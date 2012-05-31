package com.github.mysql2excel.model;

public class Database extends Model {

	private static final long serialVersionUID = -6825632534320534926L;

	private String name;
	private String version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
