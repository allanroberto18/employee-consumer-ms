package com.alr.employeeconsumer.configuration.client;

import com.alr.employeeconsumer.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alr.employeeconsumer.contracts.client.RestTemplateClient;
import com.alr.employeeconsumer.client.RestTemplateClientImpl;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestTemplateClientConfiguration {

	private RestTemplate restTemplate;
	private DiscoveryClient discoveryClient;
	private LoadBalancerClient loadBalancer;

	public RestTemplateClientConfiguration(
			@Autowired DiscoveryClient discoveryClient,
			@Autowired LoadBalancerClient loadBalancer,
			@Autowired RestTemplate restTemplate
	) {
		this.discoveryClient = discoveryClient;
		this.loadBalancer = loadBalancer;
		this.restTemplate = restTemplate;
	}

	@Bean
	public RestTemplateClient<Employee> getEmployeeClient() {
		return new RestTemplateClientImpl<Employee>(getServiceInstanceFromLoadBalancer(), restTemplate);
	}

	@Bean
	public RestTemplateClient<List> getEmployeesClient() {
		return new RestTemplateClientImpl<List>(getServiceInstanceFromDiscoveryClient(), restTemplate);
	}
	
	private ServiceInstance getServiceInstanceFromLoadBalancer() {
		return loadBalancer.choose("employee-producer");
	}

	private ServiceInstance getServiceInstanceFromDiscoveryClient() {
		 return discoveryClient.getInstances("employee-producer").stream().findFirst().get();
	}
}
