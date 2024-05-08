package bio.terra.annvarsearch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "annvarsearch.sam")
public record SamConfiguration(String basePath) {}
