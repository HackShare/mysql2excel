package com.github.mysql2excel;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.github.mysql2excel.database.DBAnalyzer;
import com.github.mysql2excel.model.Column;
import com.github.mysql2excel.model.Table;

public class DBAnalyzerTest {

	@Test
	public void testGetConnection() {
		Connection connection = DBAnalyzer.getConnection();
		Assert.assertNotNull(connection);
	}

	@Test
	public void testGetDatabaseInfo() {
		Map<String, Object> info = DBAnalyzer.getDatabaseInfo();
		boolean empty = info.isEmpty();
		Assert.assertFalse(empty);
		Assert.assertTrue(info.containsKey("name"));
		Assert.assertTrue(info.containsKey("version"));
	}

	@Test
	public void testGetAllTabes() {
		List<Table> list = DBAnalyzer.getTables();
		boolean empty = list.isEmpty();
		Assert.assertFalse(empty);
	}

	@Test
	public void testGetColumns() {
		List<Column> list = DBAnalyzer.getColumns("user");
		boolean empty = list.isEmpty();
		Assert.assertFalse(empty);
	}

	@Test
	public void testGetPrimaryKeys() {
		List<Column> list = DBAnalyzer.getPrimaryKeys("user");
		boolean empty = list.isEmpty();
		Assert.assertFalse(empty);
	}

}
