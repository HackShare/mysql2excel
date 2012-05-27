package com.github.mysql2excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

	private Utils() {

	}

	// --------------------------------------------

	private static Properties jdbcProperties = null;

	public static Properties loadProperties(String configFile) {

		if (jdbcProperties == null) {
			jdbcProperties = new Properties();
			InputStream is = null;
			try {
				is = new FileInputStream(configFile);
				jdbcProperties.load(is);
				return jdbcProperties;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return jdbcProperties;
	}

	public static String getJDBCConfig(String key) {
		Properties properties = loadProperties(Constants.JDBC_CONFIG_FILE);
		Object value = properties.get(key);
		if (value == null) {
			return "[" + key + "]";
		} else {
			return value.toString();
		}
	}

}
