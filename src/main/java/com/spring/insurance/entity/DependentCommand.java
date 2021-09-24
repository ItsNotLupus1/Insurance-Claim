package com.spring.insurance.entity;

import org.springframework.util.AutoPopulatingList;

public class DependentCommand {
	
	private AutoPopulatingList<Dependents> depends;

	public AutoPopulatingList<Dependents> getDepends() {
		return depends;
	}

	public void setDepends(AutoPopulatingList<Dependents> depends) {
		this.depends = depends;
	}
	
	

}
