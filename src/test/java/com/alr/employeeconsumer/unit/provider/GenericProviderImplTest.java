package com.alr.employeeconsumer.unit.provider;

import com.alr.employeeconsumer.contracts.client.RestTemplateClient;
import com.alr.employeeconsumer.provider.GenericProviderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GenericProviderImplTest {

  @Mock
  private RestTemplateClient<List> restTemplateClient;

  private GenericProviderImpl<List> genericProvider;

  @BeforeEach
  void setUp() {
    genericProvider = new GenericProviderImpl<>(
        restTemplateClient
    );
  }

  @Test
  public void shouldReturnValidResponse() throws IOException {
    String url = "http://localhost:8080";
    Mockito.when(restTemplateClient.get(url, List.class)).thenReturn(List.of(1, 2, 3));

    List<Integer> result = genericProvider.getClientResponse(url, List.class);

    Assertions.assertEquals(3, result.size());
  }
}
