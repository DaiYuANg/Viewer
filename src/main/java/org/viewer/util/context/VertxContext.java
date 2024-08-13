package org.viewer.util.context;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@UtilityClass
public class VertxContext {

  public <T> Uni<T> rollbackContext(@NonNull Supplier<Uni<T>> uni, @NonNull Vertx vertx) {
    return rollbackContext(uni.get(), vertx);
  }

  public <T> Uni<T> rollbackContext(@NonNull Uni<T> uni, @NonNull Vertx vertx) {
    val currentContext = vertx.getOrCreateContext();
    return uni.emitOn(currentContext::runOnContext);
  }
}
