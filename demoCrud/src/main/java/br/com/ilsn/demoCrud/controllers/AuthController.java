package br.com.ilsn.demoCrud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.ilsn.demoCrud.models.User;

@Controller
public class AuthController {

	@GetMapping("/login")
	public String login(Model model) {
		return "auth/login";
	}
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "auth/registration";
	}
}
 