package com.github.mysql2excel;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class DBHelperTest {

	@Test
	public void testGetConnection() {
		Connection connection = DBHelper.getConnection();
		Assert.assertNotNull(connection);
	}

	@Test
	public void testGetAllTabes() {
		List<Map<String, Object>> list = DBHelper.getTables();
		boolean empty = list.isEmpty();
		Assert.assertFalse(empty);
	}

	@Test
	public void testGetColumns() {
		List<Map<String, Object>> list = DBHelper.getColumns("user");
		boolean empty = list.isEmpty();
		Assert.assertFalse(empty);
	}
	
	@Test
	public void testGetPrimaryKeys() {
		List<Map<String, Object>> list = DBHelper.getPrimaryKeys("user");
		boolean empty = list.isEmpty();
		Assert.assertFalse(empty);
	}

}
