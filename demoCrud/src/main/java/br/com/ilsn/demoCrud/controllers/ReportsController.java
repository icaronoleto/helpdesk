/*package br.com.ilsn.demoCrud.controllers;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.ilsn.demoCrud.models.Product;
import br.com.ilsn.demoCrud.services.ProductService;
import br.com.ilsn.demoCrud.reports.ProductReportPdf;

@Controller
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	private ProductService productService;
	
	public ReportsController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/products")
	public String productReport(@RequestParam(required = false, value = "day") Integer day, Model model) { 
		model.addAttribute("list", this.productService.findAll());
		return "reports/product"; 
	}
	
	@GetMapping("/products/pdfgen")
	public String productReportPdfForm(Model model) { 
		return "reports/product_pdf"; 
	}
	
	@GetMapping(value = "/products/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> pdfProductReport(@RequestParam(required = false, value = "day") Integer day, Model model) {
		List<Product> products = this.productService.findAll();
				
		ByteArrayInputStream pdf = ProductReportPdf.generate(products);
		
		return ResponseEntity
					.ok()
					.header("Content-Disposition", "inline; filname=report.pdf")
					.contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(pdf));
	}
}
*/