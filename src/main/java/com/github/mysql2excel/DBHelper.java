package com.github.mysql2excel;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBHelper {

	public static Connection getConnection() {
		Connection conn = null;
		Properties config = Utils.loadProperties(Constants.JDBC_CONFIG_FILE);
		String driver = config.getProperty("jdbc.driver");
		String url = config.getProperty("jdbc.url");
		String user = config.getProperty("jdbc.user");
		String pass = config.getProperty("jdbc.pass");

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static DatabaseMetaData getDatabaseMetaData() {
		Connection con = getConnection();
		DatabaseMetaData meta = null;
		try {
			meta = con.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return meta;
	}

	public static List<Map<String, Object>> getAllTables() {
		DatabaseMetaData m = getDatabaseMetaData();
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = "%";
		String[] types = { "TABLE" };
		List<Map<String, Object>> tableList = new ArrayList<Map<String, Object>>();
		try {
			ResultSet rs = m.getTables(catalog, schemaPattern,
					tableNamePattern, types);
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				String tableType = rs.getString("TABLE_TYPE");
				Map<String, Object> table = new HashMap<String, Object>();
				table.put("name", tableName);
				table.put("type", tableType);
				tableList.add(table);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableList;
	}

}
