package com.example.inventorymanagement.Mapper;


import com.example.inventorymanagement.DTO.UserDTO;
import com.example.inventorymanagement.model.User;
import com.example.inventorymanagement.model.Authority;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setEnabled(user.isEnabled());
        dto.setCredentialsNonExpired(user.isCredentialsNonExpired());

        Set<String> authorityNames = user.getAuthorities()
                .stream()
                .map(Authority::getName)
                .collect(Collectors.toSet());

        dto.setAuthorities(authorityNames);

        return dto;
    }
}

