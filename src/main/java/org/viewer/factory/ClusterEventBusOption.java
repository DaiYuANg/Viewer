package org.viewer.factory;

import com.google.auto.factory.AutoFactory;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.tracing.TracingPolicy;
import lombok.extern.slf4j.Slf4j;

@AutoFactory
@Slf4j
public class ClusterEventBusOption extends DeliveryOptions {
  {
    setLocalOnly(false);
    setTracingPolicy(TracingPolicy.ALWAYS);
  }
}
