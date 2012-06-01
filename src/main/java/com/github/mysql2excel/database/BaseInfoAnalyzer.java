package com.github.mysql2excel.database;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.github.mysql2excel.model.Database;

public class BaseInfoAnalyzer implements Analyzer {

	private static Logger logger = Logger.getLogger(BaseInfoAnalyzer.class);

	public Database analyse() {
		logger.info("get database info.");
		DatabaseMetaData meta = DAQ.getDatabaseMetaData();
		Database db = new Database();
		try {
			String version = meta.getDatabaseProductVersion();
			String name = meta.getDatabaseProductName();
			db.setName(name);
			db.setVersion(version);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return db;
	}

}
