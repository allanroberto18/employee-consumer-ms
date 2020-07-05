package com.alr.employeeconsumer.contracts.service;

import com.alr.employeeconsumer.model.Employee;

import java.util.List;

public interface EmployeeConsumerService {

  Employee getEmployee(final Integer empId);
  List<Employee> getEmployees();
}
