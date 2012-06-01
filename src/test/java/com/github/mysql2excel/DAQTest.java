package com.github.mysql2excel;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.mysql2excel.database.DAQ;
import com.github.mysql2excel.model.Database;
import com.github.mysql2excel.model.Table;

public class DAQTest {

	@Test
	public void testGetDatabaseInfo() {
		Database db = DAQ.getBaseInfo();
		Assert.assertNotNull(db);
		Assert.assertNotNull(db.getName());
		Assert.assertNotNull(db.getVersion());
	}

	@Test
	public void testGetAllTabes() {
		List<Table> list = DAQ.getTables();
		Assert.assertNotNull(list);
		boolean empty = list.isEmpty();
		Assert.assertFalse(empty);
	}

//	@Test
//	public void testGetColumns() {
//		List<Column> list = DBAnalyzer.getColumns("user");
//		Assert.assertNotNull(list);
//		boolean empty = list.isEmpty();
//		Assert.assertFalse(empty);
//	}
//
//	@Test
//	public void testGetPrimaryKeys() {
//		List<Column> list = DBAnalyzer.getPrimaryKeys("user");
//		boolean empty = list.isEmpty();
//		Assert.assertFalse(empty);
//	}

}
