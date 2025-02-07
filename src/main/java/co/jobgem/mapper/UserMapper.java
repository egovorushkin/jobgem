package co.jobgem.mapper;

import co.jobgem.dto.UserDTO;
import co.jobgem.entity.Role;
import co.jobgem.entity.RoleName;
import co.jobgem.entity.UserEntity;
import java.util.Collections;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToStringSet")
    UserDTO userToUserDTO(UserEntity user);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "stringSetToRoles")
    UserEntity userDTOToUser(UserDTO userDTO);

    @Named("rolesToStringSet")
    default Set<String> rolesToStringSet(Set<Role> roles) {
        if (roles == null) {
            return Collections.emptySet();
        }
        return roles.stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
    }

    @Named("stringSetToRoles")
    default Set<Role> stringSetToRoles(Set<String> roleNames) {
        if (roleNames == null) {
            return Collections.emptySet();
        }
        return roleNames.stream()
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(RoleName.valueOf(roleName));
                    return role;
                })
                .collect(Collectors.toSet());
    }
}

