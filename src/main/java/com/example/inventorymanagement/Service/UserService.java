package com.example.inventorymanagement.Service;

import com.example.inventorymanagement.model.Authority;
import com.example.inventorymanagement.model.User;
import com.example.inventorymanagement.repository.AuthorityRepository;
import com.example.inventorymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    @Autowired
    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    // Create new user
    public User createUser(User user) {
        Set<Authority> resolvedAuthorities = new HashSet<>();
        for (Authority authority : user.getAuthorities()) {
            authorityRepository.findByName(authority.getName())
                    .ifPresentOrElse(resolvedAuthorities::add,
                            () -> {
                                throw new RuntimeException("Authority not found: " + authority.getName());
                            });
        }
        user.setAuthorities(resolvedAuthorities);
        return userRepository.save(user);
    }


    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // Get user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Update user
    public User updateUser(Integer id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setEnabled(updatedUser.isEnabled());
            user.setCredentialsNonExpired(updatedUser.isCredentialsNonExpired());
            user.setAccountNonLocked(updatedUser.isAccountNonLocked());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Delete user
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    // Enable user
    public void enableUser(Integer id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setEnabled(true);
            userRepository.save(user);
        });
    }

    // Disable user
    public void disableUser(Integer id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setEnabled(false);
            userRepository.save(user);
        });
    }

    // Lock user
    public void lockUser(Integer id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setAccountNonLocked(false);
            userRepository.save(user);
        });
    }

    // Unlock user
    public void unlockUser(Integer id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setAccountNonLocked(true);
            userRepository.save(user);
        });
    }

    // Change password
    public void changePassword(Integer id, String newPassword) {
        userRepository.findById(id).ifPresent(user -> {
            user.setPassword(newPassword);
            userRepository.save(user);
        });
    }
}
