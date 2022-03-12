package br.com.ilsn.demoCrud.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ilsn.demoCrud.models.Role;
import br.com.ilsn.demoCrud.services.RoleService;

@Controller
@RequestMapping("/roles")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController extends GenericController<Role>{
	
	@Autowired
	private RoleService roleService;

	public RoleController(RoleService roleService) {
		super(roleService, Role.class);
		this.roleService = roleService;
	}
	
	@PostMapping
	@Override
	public String save( @Valid @ModelAttribute("role") Role role, BindingResult bindingResult, Model model) {
		return super.save(role, bindingResult, model);
	}
	
	@PostMapping("/e/{id}")
	@Override
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("role") Role role, BindingResult bindingResult, Model model) {
		return super.update(id, role, bindingResult, model);
	}

}
