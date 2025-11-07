package com.savetpeople.controller;

import com.savetpeople.model.Job;
import com.savetpeople.repository.JobRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobRepository repo;

    public JobController(JobRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Job> all() { return repo.findAll(); }

    @PostMapping
    public Job create(@RequestBody Job j) { return repo.save(j); }

    @GetMapping("/{id}")
    public ResponseEntity<Job> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
