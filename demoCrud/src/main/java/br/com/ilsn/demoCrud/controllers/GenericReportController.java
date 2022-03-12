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

import br.com.ilsn.demoCrud.reports.GenericReportPdf;
import br.com.ilsn.demoCrud.services.GenericService;

@Controller
public abstract class GenericReportController<T> {

	@Autowired
	private GenericService<T> objService;
	
	private String objClassNameSingular;
	
	public GenericReportController(GenericService<T> objService, Class<T> clazz) {
		this.objService = objService;
		this.objClassNameSingular = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1);
	}
	
	@GetMapping
	public String objectFindAllReport(Model model) { 
		model.addAttribute("list", this.objService.findAll());
		return "reports/"+this.objClassNameSingular; 
	}
	
	@GetMapping("/pdfgen")
	public String objectReportPdfForm(Model model) { 
		return "reports/"+this.objClassNameSingular+"_pdf"; 
	}
	
//	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
//	public ResponseEntity<InputStreamResource> pdfObjectFindAllReport(ByteArrayOutputStream byteArrayOutputStream, Model model) {
//				
////		GenericReportPdf<T> grp = new GenericReportPdf<T>() {};
//		ByteArrayInputStream pdf = grp.generate(byteArrayOutputStream);
//		
//		return ResponseEntity
//					.ok()
//					.header("Content-Disposition", "inline; filname=report.pdf")
//					.contentType(MediaType.APPLICATION_PDF)
//					.body(new InputStreamResource(pdf));
//	}
}
