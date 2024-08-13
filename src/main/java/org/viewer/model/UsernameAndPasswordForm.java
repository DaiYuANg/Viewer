package org.viewer.model;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record UsernameAndPasswordForm(
  String username,
  String password
) {
}
