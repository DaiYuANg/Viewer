package org.viewer.converter;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.viewer.entity.InternalUser;
import org.viewer.model.UsernameAndPasswordForm;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface InternalUserConverter {

  InternalUser usernameAndPassword2InternalUser(UsernameAndPasswordForm usernameAndPasswordForm);
}
