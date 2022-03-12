package br.com.ilsn.demoCrud.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.ilsn.demoCrud.models.Product;
import br.com.ilsn.demoCrud.reports.ProductReportPdf;
import br.com.ilsn.demoCrud.services.ProductService;

@Controller
@RequestMapping("/reports/products")
public class ProductReportsController extends GenericReportController<Product>{

	@Autowired
	private ProductService productService;
	
	public ProductReportsController(ProductService productService) {
		super(productService, Product.class);
		this.productService = productService;
	}
	
	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> pdfProductReport(Model model) {
		List<Product> products = this.productService.findAll();
				
		ByteArrayInputStream pdf = ProductReportPdf.generate(products);
		
		return ResponseEntity
					.ok()
					.header("Content-Disposition", "inline; filname=report.pdf")
					.contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(pdf));
	}

}
