package com.github.mysql2excel.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.github.mysql2excel.helper.Constants;
import com.github.mysql2excel.helper.M2EHelper;
import com.github.mysql2excel.model.Column;
import com.github.mysql2excel.model.Database;
import com.github.mysql2excel.model.Table;

public class DBAnalyzer {

	private static Logger logger = Logger.getLogger(DBAnalyzer.class);

	public static Connection getConnection() {

		logger.info("Try to connect to database...");

		Connection conn = null;
		Properties config = M2EHelper
				.loadProperties(Constants.JDBC_CONFIG_FILE);
		String driver = config.getProperty("jdbc.driver");
		String url = config.getProperty("jdbc.url");
		String user = config.getProperty("jdbc.user");
		String pass = config.getProperty("jdbc.pass");

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			logger.error("Database connection failed!", e);
		}
		logger.info("Database connection success :)");
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

	public static Database getDatabaseInfo() {
		DatabaseMetaData meta = getDatabaseMetaData();
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

	public static List<Table> getTables() {
		DatabaseMetaData m = getDatabaseMetaData();
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

	public static List<Column> getColumns(String table) {
		DatabaseMetaData m = getDatabaseMetaData();
		List<Column> columnList = new ArrayList<Column>();

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
				Column column = new Column();
				column.setName(name);
				column.setType(type);
				column.setSize(size);
				columnList.add(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return columnList;
	}

	public static List<Column> getPrimaryKeys(String table) {
		DatabaseMetaData m = getDatabaseMetaData();
		List<Column> columnList = new ArrayList<Column>();

		String catalog = null;
		String schema = null;
		String tableNamePattern = table;
		try {
			ResultSet rs = m.getPrimaryKeys(catalog, schema, tableNamePattern);
			while (rs.next()) {
				String name = rs.getString("COLUMN_NAME");
				Column column = new Column();
				column.setName(name);
				columnList.add(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return columnList;
	}

}
