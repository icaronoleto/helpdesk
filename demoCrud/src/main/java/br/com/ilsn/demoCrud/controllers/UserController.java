package br.com.ilsn.demoCrud.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ilsn.demoCrud.models.Role;
import br.com.ilsn.demoCrud.models.User;
import br.com.ilsn.demoCrud.services.RoleService;
import br.com.ilsn.demoCrud.services.UserService;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController extends GenericController<User>{
	
	@Autowired 
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	public UserController(UserService userService, RoleService roleService) {
		super(userService, User.class);
		this.userService = userService;
		this.roleService = roleService;
	}
	
	@PostMapping
	@Override
	public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		return super.save(user, bindingResult, model);
	}
	
	@PostMapping("/e/{id}")
	@Override
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		return super.update(id, user, bindingResult, model);
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		List<Role> roles = this.roleService.findAll();
		model.addAttribute("listRoles", roles);
		
		return super.edit(id, model);
	}
	
}
