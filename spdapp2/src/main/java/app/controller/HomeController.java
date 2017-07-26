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
@RequestMapping(value = "/")
public class HomeController {

	private static final String PARAM_ROLE_USER = "ROLE_USER";

	@Autowired(required = true)
	private CustomUserRepository userRepository;

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String register(@ModelAttribute CustomUser user) {
		user.setRole(PARAM_ROLE_USER);
		user = userRepository.save(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, 
				user.getPassword(), user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String goRegister() {
		return "register";
	}

	@RequestMapping(value = { "/", "main" }, method = RequestMethod.GET)
	public String goHome() {
		return "main";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String goLogin() {
		return "login";
	}

}
