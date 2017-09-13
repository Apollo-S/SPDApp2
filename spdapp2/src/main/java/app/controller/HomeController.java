package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.entity.CustomUser;
import app.repository.CustomUserRepository;

@Controller
@RequestMapping(value = BaseController.REQUEST_MAPPING_BLANK)
public class HomeController extends BaseController {

	@Autowired(required = true)
	private CustomUserRepository userRepository;

	@RequestMapping(value = REQUEST_MAPPING_REGISTER, method = RequestMethod.POST)
	public String register(@ModelAttribute CustomUser user) {
		user.setRole(ROLE_USER);
		user = userRepository.save(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, 
				user.getPassword(), user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}

	@RequestMapping(value = REQUEST_MAPPING_REGISTER, method = RequestMethod.GET)
	public String goRegister() {
		return "register";
	}
	
	@RequestMapping(value = {REQUEST_MAPPING_BLANK, REQUEST_MAPPING_ABOUT}, method = RequestMethod.GET)
	public String goAbout() {
		return "about";
	}
	
	@RequestMapping(value = REQUEST_MAPPING_LOGIN, method = RequestMethod.GET)
	public String goLogin() {
		return "login";
	}

}
