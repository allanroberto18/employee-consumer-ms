package com.alr.employeeconsumer.client;

import com.alr.employeeconsumer.contracts.client.RestTemplateClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.client.RestTemplate;

public class RestTemplateClientImpl<V> implements RestTemplateClient<V> {

	private ServiceInstance serviceInstance;
	private RestTemplate restTemplate;
	private Logger logger = LoggerFactory.getLogger(RestTemplateClientImpl.class);
		
	public RestTemplateClientImpl(
			ServiceInstance serviceInstance,
			RestTemplate restTemplate
	) {
		this.serviceInstance = serviceInstance;
		this.restTemplate = restTemplate;
	}
	
	public V get(final String path, Class<V> clazz) {
		V response = null;
		try {
			String url = String.format("%s/%s", serviceInstance.getUri().toString(), path);
			response = (V) this.restTemplate.getForObject(url, clazz);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return response;
	}
}
