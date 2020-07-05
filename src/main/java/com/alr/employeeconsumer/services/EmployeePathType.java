package com.alr.employeeconsumer.services;

public enum EmployeePathType {
	
	EmployeeProducer("/producer/employee"),
	EmployeesProducer("/producer/employees"),
	;
	
	
	private EmployeePathType(String value) {
		this.value = value;
	}

	private String value;
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
}
