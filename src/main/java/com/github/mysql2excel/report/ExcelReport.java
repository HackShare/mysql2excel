package com.github.mysql2excel.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.github.mysql2excel.database.Analyzer;
import com.github.mysql2excel.database.DAQ;
import com.github.mysql2excel.database.TableAnalyzer;
import com.github.mysql2excel.model.Database;
import com.github.mysql2excel.model.Table;

public class ExcelReport extends ReportTemplate {

	private static Logger logger = Logger.getLogger(ExcelReport.class);

	private Workbook workbook = null;
	private String fileName;

	public ExcelReport(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void init() {
		logger.info("Init report.");
		if (workbook == null) {
			workbook = new SXSSFWorkbook(100);
		}
	}

	@Override
	public void database() {
		logger.info("Generate database information.");
		Database x = DAQ.getBaseInfo();
		Sheet database = workbook.createSheet("Database Info");

		Cell r11 = cell(database, 1, 1, CellStyles.getStyle("title"));
		r11.setCellValue(x.getName() + " - " + x.getVersion());

	}

	private Cell cell(Sheet sheet, int rowIndex, int columnIndex,
			CellStyle style) {
		Row row = sheet.getRow(rowIndex);
		if (row == null) {
			row = sheet.createRow(rowIndex);
		}
		Cell cell = row.createCell(columnIndex);
		cell.setCellStyle(style);

		return cell;
	}

	@Override
	public void table() {
		logger.info("Generate table list.");
		Analyzer a = new TableAnalyzer();
		@SuppressWarnings("unchecked")
		List<Table> list = (List<Table>) a.analyse();
		workbook.createSheet("Table List");
		for (Table table : list) {
			table.getName();
		}
	}

	@Override
	public void view() {
		logger.info("Generate view list.");
		workbook.createSheet("View List");
	}

	@Override
	public void data() {
		logger.info("Generate data storage.");
		workbook.createSheet("Data Info");
	}

	@Override
	public void functionAndProcedure() {
		logger.info("Generate function and procedure.");
		workbook.createSheet("Function & Procedure");
	}

	@Override
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
