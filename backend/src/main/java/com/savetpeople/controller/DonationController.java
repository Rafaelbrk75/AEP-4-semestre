package com.savetpeople.controller;

import com.savetpeople.model.Donation;
import com.savetpeople.repository.DonationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {
    private final DonationRepository repo;

    public DonationController(DonationRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Donation> all() { return repo.findAll(); }

    @PostMapping
    public Donation create(@RequestBody Donation d) { return repo.save(d); }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
