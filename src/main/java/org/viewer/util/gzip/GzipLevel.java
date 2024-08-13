package org.viewer.util.gzip;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.compressors.gzip.GzipParameters;

@RequiredArgsConstructor
@Getter
public enum GzipLevel {

  LOW(
    new GzipParameters() {{
      setCompressionLevel(1);
    }}
  ),

  MEDIA(new GzipParameters()),

  HIGH(new GzipParameters());

  private final GzipParameters gzipParameters;
}
