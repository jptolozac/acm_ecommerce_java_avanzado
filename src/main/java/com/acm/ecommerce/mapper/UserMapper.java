package com.acm.ecommerce.mapper;
import com.acm.ecommerce.dto.UserDTO;
import com.acm.ecommerce.entities.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name")
    @Mapping(target = "address")
    @Mapping(target = "email")
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    UserEntity toEntity(UserDTO.UserRequest request);

    UserDTO.UserResponse toResponse(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(UserDTO.UserRequest request, @MappingTarget UserEntity entity);

}
