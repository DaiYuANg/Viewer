package org.viewer.converter;

import io.minio.ObjectWriteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.viewer.entity.CloneHistory;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface CloneHistoryConverter {

  @Mapping(target = "bucket", expression = "java(writeResponse.bucket())")
  @Mapping(target = "object", expression = "java(writeResponse.object())")
  CloneHistory create(ObjectWriteResponse writeResponse);
}
