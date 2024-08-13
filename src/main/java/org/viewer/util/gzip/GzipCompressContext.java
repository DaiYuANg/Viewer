package org.viewer.util.gzip;

import io.soabase.recordbuilder.core.RecordBuilder;
import lombok.NonNull;
import lombok.val;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import java.io.File;
import java.io.IOException;

@RecordBuilder
public record GzipCompressContext(
  @NonNull
  File file,
  @NonNull
  TarArchiveOutputStream taos,
  @NonNull String parent
) {


}
