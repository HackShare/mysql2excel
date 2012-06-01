package com.github.mysql2excel;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import com.github.mysql2excel.helper.DBConnection;

public class DBConnectionTest {

	@Test
	public void testNewInstance() {
		Connection connection = DBConnection.newInstance();
		Assert.assertNotNull(connection);
	}

}
