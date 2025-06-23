package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.DTO.UserDTO;
import com.example.inventorymanagement.model.EAuthority;
import com.example.inventorymanagement.model.User;
import com.example.inventorymanagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        Optional<UserDTO> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/add-role/{role}")
    public ResponseEntity<UserDTO> addRole(@PathVariable Integer id, @PathVariable EAuthority role) {
        return ResponseEntity.ok(userService.addRoleToUser(id, role));
    }

    @PostMapping("/{id}/remove-role/{role}")
    public ResponseEntity<UserDTO> removeRole(@PathVariable Integer id, @PathVariable EAuthority role) {
        return ResponseEntity.ok(userService.removeRoleFromUser(id, role));
    }
    @GetMapping("/dto/{id}")
    public ResponseEntity<UserDTO> getUserDTO(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/full/{id}")
    public ResponseEntity<User> getFullUser(@PathVariable Integer id) {
        Optional<User> user = userService.getRawUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}