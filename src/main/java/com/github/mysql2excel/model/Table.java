package com.github.mysql2excel.model;

import com.github.mysql2excel.Utils;

public class Table {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return Utils.toString(this);
	}
}
