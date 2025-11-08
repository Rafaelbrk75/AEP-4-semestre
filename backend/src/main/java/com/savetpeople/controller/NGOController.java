package com.savetpeople.controller;

import com.savetpeople.model.NGO;
import com.savetpeople.repository.NGORepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ongs")
public class NGOController {
    private final NGORepository repo;

    public NGOController(NGORepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<NGO> all() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<NGO> create(@Valid @RequestBody NGO ngo) {
        NGO saved = repo.save(ngo);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NGO> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NGO> update(@PathVariable Long id, @Valid @RequestBody NGO ngo) {
        return repo.findById(id)
                .map(existing -> {
                    if (ngo.getName() != null) existing.setName(ngo.getName());
                    if (ngo.getContact() != null) existing.setContact(ngo.getContact());
                    if (ngo.getDescription() != null) existing.setDescription(ngo.getDescription());
                    if (ngo.getPhone() != null) existing.setPhone(ngo.getPhone());
                    if (ngo.getAddress() != null) existing.setAddress(ngo.getAddress());
                    if (ngo.getWebsite() != null) existing.setWebsite(ngo.getWebsite());
                    if (ngo.getLocation() != null) existing.setLocation(ngo.getLocation());
                    if (ngo.getIsVerified() != null) existing.setIsVerified(ngo.getIsVerified());
                    return ResponseEntity.ok(repo.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
