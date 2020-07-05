package com.alr.employeeconsumer.integration.services;

import com.alr.employeeconsumer.contracts.provider.GenericProvider;
import com.alr.employeeconsumer.contracts.service.EmployeeConsumerService;
import com.alr.employeeconsumer.model.Employee;
import com.alr.employeeconsumer.services.EmployeePathType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeConsumerServiceTest {

  @Autowired
  private EmployeeConsumerService employeeService;

  @Autowired
  @Qualifier("employeesProvider")
  private GenericProvider<List> employeesProvider;

  @Test
  public void shouldReturnEmployee() throws Exception {
    Integer empId = 1;
    Employee employee = employeeService.getEmployee(empId);

    Assertions.assertTrue(empId == employee.getEmpId());
  }


  @Test
  public void shouldReturnListOfEmployees() throws Exception {
    List<Employee> result = employeeService.getEmployees();

    Assertions.assertTrue( result instanceof List);
  }
}
