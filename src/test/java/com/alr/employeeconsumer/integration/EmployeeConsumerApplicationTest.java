package com.alr.employeeconsumer.integration;

import com.alr.employeeconsumer.contracts.client.RestTemplateClient;
import com.alr.employeeconsumer.contracts.provider.GenericProvider;
import com.alr.employeeconsumer.contracts.service.EmployeeConsumerService;
import com.alr.employeeconsumer.controller.EmployeeController;
import com.alr.employeeconsumer.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeConsumerApplicationTest {

  @Autowired
  private EmployeeConsumerService employeeConsumerService;

  @Autowired
  @Qualifier("employeeProvider")
  private GenericProvider<Employee> employeeProvider;

  @Autowired
  @Qualifier("employeesProvider")
  private GenericProvider<List> employeesProvider;

  @Autowired
  @Qualifier("getEmployeeClient")
  private RestTemplateClient<Employee> employeeRestTemplateClient;

  @Autowired
  @Qualifier("getEmployeesClient")
  private RestTemplateClient<List> employeesRestTemplateClient;

  @Autowired
  private EmployeeController employeeController;

  @Test
  public void contextLoads() throws Exception {
    Assertions.assertTrue(employeesProvider != null);
    Assertions.assertTrue(employeesRestTemplateClient != null);
    Assertions.assertTrue(employeeProvider != null);
    Assertions.assertTrue(employeeRestTemplateClient != null);
    Assertions.assertTrue(employeeConsumerService != null);
    Assertions.assertTrue(employeeController != null);
  }
}
