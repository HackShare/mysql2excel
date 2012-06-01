package com.github.mysql2excel.database;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import com.github.mysql2excel.helper.DBConnection;
import com.github.mysql2excel.model.Database;
import com.github.mysql2excel.model.Table;
import com.github.mysql2excel.model.View;

public class DAQ {

	public static DatabaseMetaData getDatabaseMetaData() {
		DatabaseMetaData meta = null;
		try {
			meta = DBConnection.newInstance().getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return meta;
	}

	public static Database getBaseInfo() {
		Analyzer a = new BaseInfoAnalyzer();
		Database db = (Database) a.analyse();
		return db;
	}

	public static List<Table> getTables() {
		Analyzer a = new TableAnalyzer();
		@SuppressWarnings("unchecked")
		List<Table> list = (List<Table>) a.analyse();
		return list;
	}

	public static List<View> getViews() {
		Analyzer a = new ViewAnalyzer();
		@SuppressWarnings("unchecked")
		List<View> list = (List<View>) a.analyse();
		return list;
	}

	public static Object getDataInfo() {
		Analyzer a = new DataAnalyzer();
		return a.analyse();
	}

	public static Object getFun() {
		Analyzer a = new FunAnalyzer();
		return a.analyse();
	}

	public static Object getSP() {
		Analyzer a = new SPAnalyzer();
		return a.analyse();
	}

}
