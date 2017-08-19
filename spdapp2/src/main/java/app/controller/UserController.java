package app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.repository.CustomUserRepository;

@Controller
@Transactional
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(SPDController.class);

	@Autowired(required = true)
	private CustomUserRepository userRepository;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		logger.info("Entering to the getAllUsers() method***");
		model.addAttribute("users", userRepository.findAll());
		return "user/getAll";
	}

}
