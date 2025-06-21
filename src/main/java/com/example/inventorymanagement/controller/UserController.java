package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.DTO.UserDTO;
import com.example.inventorymanagement.Mapper.UserMapper;
import com.example.inventorymanagement.model.User;
import com.example.inventorymanagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create new user
    @PostMapping("/add")
    public String createUser(@RequestBody User user) {
        userService.createUser(user);
        return "New user added successfully!";
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isPresent()) {
            UserDTO dto = UserMapper.toDTO(userOpt.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Get user by email
    @GetMapping("/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Get user by username
    @GetMapping("/username/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    // Update user
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user);
        return "User updated successfully!";
    }

    // Delete user
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "User deleted successfully!";
    }

    // Enable user
    @PutMapping("/enable/{id}")
    public String enableUser(@PathVariable Integer id) {
        userService.enableUser(id);
        return "User enabled successfully!";
    }

    // Disable user
    @PutMapping("/disable/{id}")
    public String disableUser(@PathVariable Integer id) {
        userService.disableUser(id);
        return "User disabled successfully!";
    }

    // Lock user
    @PutMapping("/lock/{id}")
    public String lockUser(@PathVariable Integer id) {
        userService.lockUser(id);
        return "User locked successfully!";
    }

    // Unlock user
    @PutMapping("/unlock/{id}")
    public String unlockUser(@PathVariable Integer id) {
        userService.unlockUser(id);
        return "User unlocked successfully!";
    }

    // Change password
    @PutMapping("/change-password/{id}")
    public String changePassword(@PathVariable Integer id, @RequestBody String newPassword) {
        userService.changePassword(id, newPassword);
        return "Password changed successfully!";
    }
}

