package com.github.mysql2excel.report;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

public class CellStyles {

	private static Map<String, CellStyle> cellStyles = new HashMap<String, CellStyle>();

	public void init(Workbook workbook) {

		CellStyle table = workbook.createCellStyle();
		border(table);
		cellStyles.put("table", table);

		CellStyle title = workbook.createCellStyle();
		border(title);
		bold(title, workbook);
		cellStyles.put("title", title);
	}

	private void border(CellStyle title) {
		title.setBorderBottom(CellStyle.BORDER_THIN);
		title.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		title.setBorderLeft(CellStyle.BORDER_THIN);
		title.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		title.setBorderRight(CellStyle.BORDER_THIN);
		title.setRightBorderColor(IndexedColors.BLACK.getIndex());
		title.setBorderTop(CellStyle.BORDER_THIN);
		title.setTopBorderColor(IndexedColors.BLACK.getIndex());
	}

	private void bold(CellStyle title, Workbook workbook) {
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		title.setFont(font);
	}

	public static CellStyle getStyle(String key) {
		if (cellStyles.isEmpty()) {
			// TODO Init styles.
		}
		return cellStyles.get(key);
	}
}
