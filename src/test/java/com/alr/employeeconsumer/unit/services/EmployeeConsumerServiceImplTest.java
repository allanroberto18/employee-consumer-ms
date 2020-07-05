package com.alr.employeeconsumer.unit.services;

import com.alr.employeeconsumer.contracts.provider.GenericProvider;
import com.alr.employeeconsumer.model.Employee;
import com.alr.employeeconsumer.services.EmployeeConsumerServiceImpl;
import com.alr.employeeconsumer.services.EmployeePathType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeConsumerServiceImplTest {

  @Mock
  private GenericProvider<Employee> employeeProvider;

  @Mock
  private GenericProvider<List> employeesProvider;

  private EmployeeConsumerServiceImpl employeeConsumerService;

  @BeforeEach
  void setUp() {
    employeeConsumerService = new EmployeeConsumerServiceImpl(
        employeeProvider,
        employeesProvider
    );
  }

  @Test
  public void shouldReturnEmployeeObject() {
    String urlPath = String.format("%s/%d", EmployeePathType.EmployeeProducer.getValue(), 1);
    Integer empId = 1;
    Employee employeeExpected = getEmployee(empId);
    Mockito.when(employeeProvider.getClientResponse(urlPath, Employee.class)).thenReturn(employeeExpected);

    Employee result = employeeConsumerService.getEmployee(empId);
    Assertions.assertTrue(result.getEmpId() == empId);
  }

  @Test
  public void shouldReturnListOfEmployees() {
    String urlPath = EmployeePathType.EmployeesProducer.getValue();
    List<Employee> employeesExpected = List.of(getEmployee(1), getEmployee(2)) ;
    Mockito.when(employeesProvider.getClientResponse(urlPath, List.class)).thenReturn(employeesExpected);

    List<Employee> result = employeeConsumerService.getEmployees();
    Assertions.assertTrue(result.size() == 2);
  }

  private Employee getEmployee(Integer empId) {
    return new Employee(empId, "emp1", "designation1", 1000d);
  }
}
