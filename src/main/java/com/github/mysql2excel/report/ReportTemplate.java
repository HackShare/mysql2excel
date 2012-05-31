package com.github.mysql2excel.report;

public abstract class ReportTemplate implements Report {

	public abstract void init();

	public abstract void database();

	public abstract void table();

	public abstract void view();

	public abstract void data();

	public abstract void functionAndProcedure();

	public abstract void writeReport();

	public void doReport() {
		this.init();
		this.database();
		this.table();
		this.view();
		this.data();
		this.functionAndProcedure();
		this.writeReport();
	}

}
