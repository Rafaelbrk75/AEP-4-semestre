package com.savetpeople.controller;

import com.savetpeople.model.User;
import com.savetpeople.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<User> all() {
        List<User> users = repo.findAll();
        // Remove passwords from response
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        // Check if email already exists
        if (user.getEmail() != null && repo.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        // Hash password if provided
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        
        User saved = repo.save(user);
        saved.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(user -> {
                    user.setPassword(null);
                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
        return repo.findById(id)
                .map(existing -> {
                    if (user.getName() != null) existing.setName(user.getName());
                    if (user.getEmail() != null) existing.setEmail(user.getEmail());
                    if (user.getAddress() != null) existing.setAddress(user.getAddress());
                    if (user.getPhone() != null) existing.setPhone(user.getPhone());
                    if (user.getCountryOfOrigin() != null) existing.setCountryOfOrigin(user.getCountryOfOrigin());
                    if (user.getDocumentNumber() != null) existing.setDocumentNumber(user.getDocumentNumber());
                    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                        existing.setPassword(passwordEncoder.encode(user.getPassword()));
                    }
                    User saved = repo.save(existing);
                    saved.setPassword(null);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
