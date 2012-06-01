package com.github.mysql2excel.database;

import org.apache.log4j.Logger;

public class DataAnalyzer implements Analyzer {

	private static Logger logger = Logger.getLogger(DataAnalyzer.class);

	public Object analyse() {
		logger.info("Do data analyse.");
		// TODO Data analyse
		return null;
	}

}
