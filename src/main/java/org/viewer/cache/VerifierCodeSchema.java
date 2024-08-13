package org.viewer.cache;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.ProtoSchema;
import org.infinispan.protostream.annotations.ProtoSyntax;

@ProtoSchema(
  includeClasses = {VerifierCode.class},
  schemaPackageName = VerifierCodeSchema.PACKAGE_NAME,
  syntax = ProtoSyntax.PROTO3
)
public interface VerifierCodeSchema extends GeneratedSchema {
  String PACKAGE_NAME = "verifier_code";
}
