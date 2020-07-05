package com.alr.employeeconsumer.controller;

import com.alr.employeeconsumer.contracts.service.EmployeeConsumerService;
import com.alr.employeeconsumer.model.Employee;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/consumer")
public class EmployeeController {

	private EmployeeConsumerService employeeConsumerService;

	public EmployeeController(EmployeeConsumerService employeeConsumerService) {
		this.employeeConsumerService = employeeConsumerService;
	}

	@GetMapping(path = "/employee/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable Integer empId) {
		return ResponseEntity.ok().body(
				employeeConsumerService.getEmployee(empId)
		);
	}

	@GetMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getEmployees() {
		return ResponseEntity.ok().body(
				employeeConsumerService.getEmployees()
		);
	}

}
