package com.github.mysql2excel.database;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.github.mysql2excel.model.View;

public class ViewAnalyzer implements Analyzer {

	private static Logger logger = Logger.getLogger(ViewAnalyzer.class);

	public Object analyse() {
		logger.info("Do view analyse.");
		DatabaseMetaData m = DAQ.getDatabaseMetaData();
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = "%";
		String[] types = { "VIEW" };
		List<View> viewList = new ArrayList<View>();
		try {
			ResultSet rs = m.getTables(catalog, schemaPattern,
					tableNamePattern, types);
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				View view = new View();
				view.setName(tableName);
				viewList.add(view);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return viewList;
	}

}
