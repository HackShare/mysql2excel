package com.github.mysql2excel;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import com.github.mysql2excel.helper.Constants;
import com.github.mysql2excel.helper.M2EHelper;

public class M2EHelperTest {

	@Test
	public void testLoadProperties() {
		Properties properties = M2EHelper
				.loadProperties(Constants.JDBC_CONFIG_FILE);
		Assert.assertNotNull(properties);
		boolean empty = properties.isEmpty();
		Assert.assertFalse(empty);
	}

	@Test
	public void testGetJDBCConfigWithParameter() {
		String testKey = M2EHelper.getJDBCConfig("test.key");
		Assert.assertEquals("mysql2excel", testKey);

		String notFoundKey = M2EHelper.getJDBCConfig("not.found.key");
		Assert.assertEquals("[not.found.key]", notFoundKey);
	}

}
