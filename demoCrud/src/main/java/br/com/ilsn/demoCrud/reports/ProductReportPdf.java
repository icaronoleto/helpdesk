package br.com.ilsn.demoCrud.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.ilsn.demoCrud.models.Product;

public class ProductReportPdf extends GenericReportPdf<Product>{

	public static ByteArrayInputStream generate(List<Product> products) {
		System.out.println("Passou aqui");
		Document doc = new Document();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			
			PdfPTable table = new PdfPTable(2);
			
			table.setWidthPercentage(100);
			table.setWidths(new int[]{1, 2});

			//THEAD = HEADER
			PdfPCell headerCell;
			
			headerCell = new PdfPCell(new Phrase("ID"));
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(headerCell);

			headerCell = new PdfPCell(new Phrase("Name"));
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(headerCell);

//			headerCell = new PdfPCell(new Phrase("Description"));
//			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			table.addCell(headerCell);
			
			//TBODY = BODY
			for (Product p: products) {
				PdfPCell bodyCell;
				
				bodyCell = new PdfPCell(new Phrase(p.getId().toString()));
				bodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(bodyCell);

				bodyCell = new PdfPCell(new Phrase(p.getName()));
				bodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(bodyCell);
				
//				bodyCell = new PdfPCell(new Phrase(p.getDescription()));
//				bodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				table.addCell(bodyCell);
			}
			
			PdfWriter.getInstance(doc, byteArrayOutputStream);
			doc.open();
			
			Paragraph title = new Paragraph(new Phrase("Product by days"));
			title.setAlignment(Element.ALIGN_CENTER);
			title.setSpacingAfter(25);
			doc.add(title);
			doc.add(table); 
			
			doc.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
//		return super.generate(byteArrayOutputStream);
	}
}
