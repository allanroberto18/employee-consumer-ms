package com.alr.employeeconsumer.contracts.provider;

public interface GenericProvider<V> {
  V getClientResponse(String urlPath, Class<V> clazz);
}
