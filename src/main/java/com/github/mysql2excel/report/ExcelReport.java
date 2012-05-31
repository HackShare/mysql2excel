package com.github.mysql2excel.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelReport extends ReportTemplate {

	private Workbook workbook = null;
	private String fileName;

	public ExcelReport(String fileName) {
		this.fileName = fileName;
	}

	public void init() {
		if (workbook == null) {
			workbook = new SXSSFWorkbook(100);
		}
	}

	public void database() {
		workbook.createSheet("Database Info");
	}

	public void table() {
		workbook.createSheet("Table List");
	}

	public void view() {
		workbook.createSheet("View List");
	}

	public void data() {
		workbook.createSheet("Data Info");
	}

	public void functionAndProcedure() {
		workbook.createSheet("Function & Procedure");
	}

	public void writeReport() {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
