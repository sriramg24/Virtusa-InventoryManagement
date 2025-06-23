package com.example.inventorymanagement.Service;

import com.example.inventorymanagement.DTO.UserDTO;
import com.example.inventorymanagement.Mapper.UserMapper;
import com.example.inventorymanagement.model.Authority;
import com.example.inventorymanagement.model.EAuthority;
import com.example.inventorymanagement.model.User;
import com.example.inventorymanagement.repository.AuthorityRepository;
import com.example.inventorymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public UserDTO createUser(User user) {
        Set<Authority> resolvedAuthorities = new HashSet<>();
        for (Authority a : user.getAuthorities()) {
            Authority found = authorityRepository.findByName(a.getName())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + a.getName()));
            resolvedAuthorities.add(found);
        }

        user.setAuthorities(resolvedAuthorities);
        return UserMapper.toDTO(userRepository.save(user));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Integer id) {
        return userRepository.findById(id).map(UserMapper::toDTO);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public UserDTO updateUser(Integer id, User updated) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(updated.getUsername());
        user.setEmail(updated.getEmail());
        user.setEnabled(updated.isEnabled());
        user.setCredentialsNonExpired(updated.isCredentialsNonExpired());
        user.setAccountNonLocked(updated.isAccountNonLocked());

        return UserMapper.toDTO(userRepository.save(user));
    }

    public UserDTO addRoleToUser(Integer userId, EAuthority role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Authority authority = authorityRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getAuthorities().add(authority);
        return UserMapper.toDTO(userRepository.save(user));
    }

    public UserDTO removeRoleFromUser(Integer userId, EAuthority role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.getAuthorities().removeIf(a -> a.getName().equals(role));
        return UserMapper.toDTO(userRepository.save(user));
    }
    public Optional<User> getRawUserById(Integer id) {
        return userRepository.findById(id);
    }

}
