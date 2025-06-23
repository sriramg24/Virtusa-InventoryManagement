package com.example.inventorymanagement.Mapper;

import com.example.inventorymanagement.DTO.UserDTO;
import com.example.inventorymanagement.model.Authority;
import com.example.inventorymanagement.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        Set<String> authorityNames = user.getAuthorities()
                .stream()
                .map(a -> a.getName().name())  // Convert enum to string
                .collect(Collectors.toSet());

        dto.setAuthorities(authorityNames);

        return dto;
    }
}
