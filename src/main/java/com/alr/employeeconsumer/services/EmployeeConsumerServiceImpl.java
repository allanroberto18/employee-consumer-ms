package com.alr.employeeconsumer.services;

import com.alr.employeeconsumer.contracts.provider.GenericProvider;
import com.alr.employeeconsumer.contracts.service.EmployeeConsumerService;
import com.alr.employeeconsumer.model.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeConsumerServiceImpl implements EmployeeConsumerService {

	private GenericProvider<Employee> employeeProvider;
	private GenericProvider<List> employeesProvider;

	public EmployeeConsumerServiceImpl(
			@Qualifier("employeeProvider") GenericProvider<Employee> employeeProvider,
			@Qualifier("employeesProvider") GenericProvider<List> employeesProvider
	) {
		this.employeeProvider = employeeProvider;
		this.employeesProvider = employeesProvider;
	}

	public Employee getEmployee(final Integer empId) {
		String urlPath = String.format("%s/%d", EmployeePathType.EmployeeProducer.getValue(), empId);
		return employeeProvider.getClientResponse(urlPath, Employee.class);
	}

	public List<Employee> getEmployees() {
		return employeesProvider.getClientResponse(EmployeePathType.EmployeesProducer.getValue(), List.class);
	}


}
