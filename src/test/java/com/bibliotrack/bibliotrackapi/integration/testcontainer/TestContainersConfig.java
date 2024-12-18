package com.bibliotrack.bibliotrackapi.integration.testcontainer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainersConfig {
  @Bean
  @ServiceConnection
  public PostgreSQLContainer<?> postgresSQLContainer() {
    return new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.0"));
  }
}
