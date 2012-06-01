package com.github.mysql2excel.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBConnection {

	private static Connection con = null;

	private static Logger logger = Logger.getLogger(DBConnection.class);

	public synchronized static Connection newInstance() {
		if (con == null) {
			logger.info("Try to connect to database...");
			Properties config = M2EHelper
					.loadProperties(Constants.JDBC_CONFIG_FILE);
			String driver = config.getProperty("jdbc.driver");
			String url = config.getProperty("jdbc.url");
			String user = config.getProperty("jdbc.user");
			String pass = config.getProperty("jdbc.pass");
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, user, pass);
				logger.info("Database connection success :)");
			} catch (Exception e) {
				logger.error("Database connection failed!", e);
			}
		}
		return con;
	}
}
