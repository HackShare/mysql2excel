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

	private static DatabaseMetaData getDatabaseMetaData() {
		Connection con = getConnection();
		DatabaseMetaData meta = null;
		try {
			meta = con.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return meta;
	}

	public static List<Map<String, Object>> getTables() {
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
				Map<String, Object> table = new HashMap<String, Object>();
				table.put("name", tableName);
				tableList.add(table);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableList;
	}

	public static List<Map<String, Object>> getColumns(String table) {
		DatabaseMetaData m = getDatabaseMetaData();
		List<Map<String, Object>> columnList = new ArrayList<Map<String, Object>>();

		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = table;
		String columnNamePattern = "%";
		try {
			ResultSet rs = m.getColumns(catalog, schemaPattern,
					tableNamePattern, columnNamePattern);
			while (rs.next()) {
				String name = rs.getString("COLUMN_NAME");
				String type = rs.getString("TYPE_NAME");
				int size = rs.getInt("COLUMN_SIZE");
				Map<String, Object> column = new HashMap<String, Object>();
				column.put("name", name);
				column.put("type", type + "(" + size + ")");
				columnList.add(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return columnList;
	}
	
	public static List<Map<String, Object>> getPrimaryKeys(String table) {
		DatabaseMetaData m = getDatabaseMetaData();
		List<Map<String, Object>> columnList = new ArrayList<Map<String, Object>>();

		String catalog = null;
		String schema = null;
		String tableNamePattern = table;
		try {
			ResultSet rs = m.getPrimaryKeys(catalog, schema, tableNamePattern);
			while (rs.next()) {
				String name = rs.getString("COLUMN_NAME");
				String pkname = rs.getString("PK_NAME");
				Map<String, Object> column = new HashMap<String, Object>();
				column.put("name", name);
				column.put("pkname", pkname);
				columnList.add(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return columnList;
	}

}
