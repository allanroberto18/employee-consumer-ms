package com.alr.employeeconsumer.provider;

import com.alr.employeeconsumer.contracts.client.RestTemplateClient;
import com.alr.employeeconsumer.contracts.provider.GenericProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

public class GenericProviderImpl<V> implements GenericProvider<V> {

  private RestTemplateClient<V> client;
  private Logger logger = LoggerFactory.getLogger(GenericProviderImpl.class);

  public GenericProviderImpl(RestTemplateClient<V> client) {
    this.client = client;
  }

  @Override
  public V getClientResponse(String urlPath, Class<V> clazz) {
    V result = null;
    try {
      result = (V) client.get(urlPath, clazz);
    } catch (RestClientException e) {
      logger.error(e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      logger.error(e.getMessage());
      e.printStackTrace();
    }

    return result;
  }
}
