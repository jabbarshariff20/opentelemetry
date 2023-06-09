package com.zinnia.mule.opentelemetry.internal.opentelemetry.sdk;

import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import io.opentelemetry.sdk.autoconfigure.spi.ResourceProvider;
import io.opentelemetry.sdk.resources.Resource;

public class MuleResourceProvider implements ResourceProvider {

  public MuleResourceProvider() {
  }

  @Override
  public Resource createResource(ConfigProperties configProperties) {
    return MuleResource.get();
  }
}
