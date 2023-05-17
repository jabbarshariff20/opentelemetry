package com.zinnia.mule.opentelemetry.internal;

import com.zinnia.mule.opentelemetry.api.config.exporter.GenericExporter;
import com.zinnia.mule.opentelemetry.api.config.exporter.LoggingExporter;
import com.zinnia.mule.opentelemetry.api.config.exporter.OpenTelemetryExporter;
import com.zinnia.mule.opentelemetry.api.config.exporter.OtlpExporter;
import com.zinnia.mule.opentelemetry.internal.config.OpenTelemetryExtensionConfiguration;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.SubTypeMapping;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;

@Xml(prefix = "opentelemetry")
@Extension(name = "OpenTelemetry")
@Configurations(OpenTelemetryExtensionConfiguration.class)
@SubTypeMapping(baseType = OpenTelemetryExporter.class, subTypes = { OtlpExporter.class, LoggingExporter.class,
    GenericExporter.class })
public class OpenTelemetryExtension {
}
