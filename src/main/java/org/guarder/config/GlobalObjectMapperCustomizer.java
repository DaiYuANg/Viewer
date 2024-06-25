package org.guarder.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.eclipsecollections.EclipseCollectionsModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.ImmutableSet;
import io.quarkus.jackson.ObjectMapperCustomizer;
import io.vertx.core.json.jackson.VertxModule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class GlobalObjectMapperCustomizer implements ObjectMapperCustomizer {

  private final ImmutableSet<Module> modules = ImmutableSet.of(
    new GuavaModule(),
    new EclipseCollectionsModule(),
    new Hibernate6Module(),
    new JavaTimeModule()
  );

  @Override
  public void customize(ObjectMapper objectMapper) {
    objectMapper.registerModules(modules);
  }
}
