package com.zinnia.mule.opentelemetry.internal.config;

import com.zinnia.mule.opentelemetry.api.config.OpenTelemetryResource;
import com.zinnia.mule.opentelemetry.api.config.SpanProcessorConfiguration;
import com.zinnia.mule.opentelemetry.api.config.exporter.OpenTelemetryExporter;

public class OpenTelemetryConfigWrapper {
  private final OpenTelemetryResource resource;
  private final OpenTelemetryExporter exporter;
  private SpanProcessorConfiguration spanProcessorConfiguration;

  public OpenTelemetryConfigWrapper(OpenTelemetryResource resource, OpenTelemetryExporter exporter,
      SpanProcessorConfiguration spanProcessorConfiguration) {
    this.resource = resource;
    this.exporter = exporter;
    this.spanProcessorConfiguration = spanProcessorConfiguration;
  }

  public OpenTelemetryResource getResource() {
    return resource;
  }

  public OpenTelemetryExporter getExporter() {
    return exporter;
  }

  public SpanProcessorConfiguration getSpanProcessorConfiguration() {
    return spanProcessorConfiguration;
  }
}
