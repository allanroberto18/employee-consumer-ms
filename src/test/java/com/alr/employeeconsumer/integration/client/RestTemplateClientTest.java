package com.alr.employeeconsumer.integration.client;

import com.alr.employeeconsumer.contracts.client.RestTemplateClient;
import com.alr.employeeconsumer.model.Employee;
import com.alr.employeeconsumer.services.EmployeePathType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RestTemplateClientTest {

  @Autowired
  @Qualifier("getEmployeeClient")
  private RestTemplateClient<Employee> employeeRestTemplateClient;

  @Autowired
  @Qualifier("getEmployeesClient")
  private RestTemplateClient<List> employeesRestTemplateClient;

  @Test
  public void shouldReturnEmployee() throws Exception {
    Integer empId = 1;
    String url = String.format("%s/%d", EmployeePathType.EmployeeProducer.getValue(), 1);
    Employee employee = employeeRestTemplateClient.get(url, Employee.class);

    Assertions.assertTrue(empId == employee.getEmpId());
  }


  @Test
  public void shouldReturnListOfEmployees() throws Exception {
    List<Employee> result = employeesRestTemplateClient.get(EmployeePathType.EmployeesProducer.getValue(), List.class);

    Assertions.assertTrue( result instanceof List);
  }
}
