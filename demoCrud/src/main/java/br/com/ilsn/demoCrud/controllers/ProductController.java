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

import br.com.ilsn.demoCrud.models.Product;
import br.com.ilsn.demoCrud.services.ProductService;

@Controller
@RequestMapping("/products")
@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController extends GenericController<Product>{

	@Autowired 
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		super(productService, Product.class);
		this.productService = productService;
		// TODO Auto-generated constructor stub
	}

	
	@PostMapping
	@Override
	public String save( @Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
		return super.save(product, bindingResult, model);
	}
	
	@PostMapping("/e/{id}")
	@Override
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
		return super.update(id, product, bindingResult, model);
	}

}
