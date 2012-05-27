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
		List<Map<String, Object>> list = DBHelper.getAllTables();
		for (Map<String, Object> table : list) {
			System.out.println(table.get("name") + ", " + table.get("type"));
		}
	}

}
