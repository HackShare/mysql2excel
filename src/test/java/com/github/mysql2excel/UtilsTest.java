package com.github.mysql2excel;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

	@Test
	public void testLoadProperties() {
		Properties properties = Utils
				.loadProperties(Constants.JDBC_CONFIG_FILE);
		Assert.assertNotNull(properties);
		boolean empty = properties.isEmpty();
		Assert.assertFalse(empty);
	}

	@Test
	public void testGetJDBCConfig2() {
		String user = Utils.getJDBCConfig("jdbc.user");
		Assert.assertEquals("root", user);
	}

}
