package com.github.mysql2excel.database;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.github.mysql2excel.model.Table;

public class TableAnalyzer implements Analyzer {

	private static Logger logger = Logger.getLogger(TableAnalyzer.class);

	public List<Table> analyse() {
		logger.info("Do table analyse.");
		DatabaseMetaData m = DAQ.getDatabaseMetaData();
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = "%";
		String[] types = { "TABLE" };
		List<Table> tableList = new ArrayList<Table>();
		try {
			ResultSet rs = m.getTables(catalog, schemaPattern,
					tableNamePattern, types);
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				Table table = new Table();
				table.setName(tableName);
				tableList.add(table);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableList;
	}

}
