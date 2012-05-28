package com.github.mysql2excel.model;

import com.github.mysql2excel.Utils;

public class Model {
	@Override
	public String toString() {
		return Utils.toString(this);
	};

	@Override
	public boolean equals(Object obj) {
		return Utils.equals(this, obj);
	}

	@Override
	public int hashCode() {
		return Utils.hashCode(this);
	}
}
