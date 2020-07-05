package com.alr.employeeconsumer.configuration.provider;

import com.alr.employeeconsumer.contracts.client.RestTemplateClient;
import com.alr.employeeconsumer.contracts.provider.GenericProvider;
import com.alr.employeeconsumer.model.Employee;
import com.alr.employeeconsumer.provider.GenericProviderImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmployeeProviderConfiguration {

  private RestTemplateClient<Employee> employeeClient;
  private RestTemplateClient<List> employeesClient;

  public EmployeeProviderConfiguration(
      @Qualifier("getEmployeeClient") RestTemplateClient<Employee> employeeClient,
      @Qualifier("getEmployeesClient") RestTemplateClient<List> employeesClient
  ) {
    this.employeeClient = employeeClient;
    this.employeesClient = employeesClient;
  }

  @Bean
  public GenericProvider<Employee> employeeProvider() {
    return new GenericProviderImpl<>(
        employeeClient
    );
  }

  @Bean
  public GenericProvider<List> employeesProvider() {
    return new GenericProviderImpl<>(
        employeesClient
    );
  }
}
