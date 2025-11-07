package com.savetpeople.controller;

import com.savetpeople.model.NGO;
import com.savetpeople.repository.NGORepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ongs")
public class NGOController {
    private final NGORepository repo;

    public NGOController(NGORepository repo) { this.repo = repo; }

    @GetMapping
    public List<NGO> all() { return repo.findAll(); }

    @PostMapping
    public NGO create(@RequestBody NGO n) { return repo.save(n); }

    @GetMapping("/{id}")
    public ResponseEntity<NGO> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
