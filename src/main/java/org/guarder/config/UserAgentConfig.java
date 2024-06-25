package org.guarder.config;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

import static io.smallrye.mutiny.Uni.createFrom;

@ApplicationScoped
public class UserAgentConfig {

  @Produces
  @Singleton
  Uni<UserAgentAnalyzer> userAgentAnalyzer() {
    return createFrom()
      .item(
        UserAgentAnalyzer.newBuilder()
          .showMinimalVersion()
          .hideMatcherLoadStats()
          .withCache(10000)
          .build()
      );
  }
}
