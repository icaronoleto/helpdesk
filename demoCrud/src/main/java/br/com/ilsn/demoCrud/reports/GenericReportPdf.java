package br.com.ilsn.demoCrud.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public abstract class GenericReportPdf<T> {
	
	public ByteArrayInputStream generate(ByteArrayOutputStream byteArrayOutputStream) {
		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	}

}
