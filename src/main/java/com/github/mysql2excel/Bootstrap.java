package com.github.mysql2excel;

import com.github.mysql2excel.report.ExcelReport;
import com.github.mysql2excel.report.Report;

public class Bootstrap {

	public static void main(String[] args) {
		String fileName = "report.xlsx";
		Report excel = new ExcelReport(fileName);
		excel.doReport();
	}

}
