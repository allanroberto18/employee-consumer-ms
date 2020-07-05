package com.alr.employeeconsumer.unit.client;

import com.alr.employeeconsumer.client.RestTemplateClientImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RestTemplateClientImplTest {

  @Mock
  private ServiceInstance serviceInstance;

  @Mock
  private RestTemplate restTemplate;

  private RestTemplateClientImpl<List> restTemplateClient;

  @BeforeEach
  void setUp() {
    restTemplateClient = new RestTemplateClientImpl<List>(
        serviceInstance,
        restTemplate
    );
  }

  @Test
  public void shouldReturnApiResponse() {
    String url = "endpoint";
    URI uri = URI.create("http://localhost:8080");

    String path = String.format("%s/%s", uri.toString(), url);
    Mockito.when(serviceInstance.getUri()).thenReturn(uri);
    Mockito.when(restTemplate.getForObject(path, List.class)).thenReturn(List.of(1, 2, 3));

    List<Integer> list = restTemplateClient.get(url, List.class);

    Assertions.assertEquals(3, list.size());
  }
}
