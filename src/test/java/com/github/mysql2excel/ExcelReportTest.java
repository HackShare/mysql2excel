package com.github.mysql2excel;

import org.junit.Test;

import com.github.mysql2excel.report.ExcelReport;
import com.github.mysql2excel.report.Report;

public class ExcelReportTest {

	@Test
	public void testReport() {
		Report report = new ExcelReport("report.xlsx");
		report.doReport();
	}

}
