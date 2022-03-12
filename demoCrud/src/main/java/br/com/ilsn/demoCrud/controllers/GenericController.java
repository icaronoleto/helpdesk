package br.com.ilsn.demoCrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.ilsn.demoCrud.services.GenericService;

@Controller
public abstract class GenericController<T> {
	@Autowired
	private GenericService<T> objService;
	
	private String objClassNameSingular;
	private String objClassNamePlural;
	private Class clazz;
	
	public GenericController(GenericService<T> objService, Class<T> clazz) {
		this.objService = objService;
		this.clazz = clazz;

		this.objClassNamePlural = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1)+"s";
		this.objClassNameSingular = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1);
	}

	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", this.objService.findAll());
		return objClassNamePlural+"/index";
	}
	
	@GetMapping("{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		T t = this.objService.show(id);
		model.addAttribute(this.objClassNameSingular, t);
		
		return this.objClassNamePlural+"/show";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		try {
			model.addAttribute(this.objClassNameSingular, clazz.newInstance());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.objClassNamePlural+"/create";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		T obj = this.objService.show(id);
		
		model.addAttribute(this.objClassNameSingular, obj);
		
		return this.objClassNamePlural+"/edit";
	}
	
	
	@PostMapping
	public String save( T obj, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return this.objClassNamePlural+"/create";
		}
		
		this.objService.create(obj);
		
		return "redirect:/"+this.objClassNamePlural;
	}
	
	@PostMapping("/e/{id}")
	public String update(@PathVariable("id") Long id, T obj, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return this.objClassNamePlural+"/edit";
		}
		try {
			this.objService.update(id, obj);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}
		
		return "redirect:/"+this.objClassNamePlural;
	}
	
	@PostMapping("/d/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		this.objService.delete(id);
		
		return "redirect:/"+this.objClassNamePlural;
	}
}
