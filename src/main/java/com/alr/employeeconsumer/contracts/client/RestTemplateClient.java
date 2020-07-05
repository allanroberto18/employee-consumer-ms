package com.alr.employeeconsumer.contracts.client;

import org.springframework.web.client.RestClientException;

import java.io.IOException;

public interface RestTemplateClient<V> {
	
	V get(final String path, Class<V> clazz) throws RestClientException, IOException;
}
