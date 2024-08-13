package org.viewer.cache;

import io.soabase.recordbuilder.core.RecordBuilder;
import org.infinispan.api.annotations.indexing.Indexed;
import org.infinispan.protostream.annotations.Proto;

@Indexed
@Proto
@RecordBuilder
public record VerifierCode(
  String email,
  String code
) {
}
