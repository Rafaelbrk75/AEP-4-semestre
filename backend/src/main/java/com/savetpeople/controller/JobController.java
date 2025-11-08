package com.savetpeople.controller;

import com.savetpeople.model.Job;
import com.savetpeople.repository.JobRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobRepository repo;

    public JobController(JobRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Job> all() {
        return repo.findAll();
    }

    @GetMapping("/active")
    public List<Job> active() {
        return repo.findByIsActiveTrue();
    }

    @PostMapping
    public ResponseEntity<Job> create(@Valid @RequestBody Job job) {
        Job saved = repo.save(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> update(@PathVariable Long id, @Valid @RequestBody Job job) {
        return repo.findById(id)
                .map(existing -> {
                    if (job.getTitle() != null) existing.setTitle(job.getTitle());
                    if (job.getDescription() != null) existing.setDescription(job.getDescription());
                    if (job.getLocation() != null) existing.setLocation(job.getLocation());
                    if (job.getCompany() != null) existing.setCompany(job.getCompany());
                    if (job.getType() != null) existing.setType(job.getType());
                    if (job.getSalary() != null) existing.setSalary(job.getSalary());
                    if (job.getRequirements() != null) existing.setRequirements(job.getRequirements());
                    if (job.getIsActive() != null) existing.setIsActive(job.getIsActive());
                    return ResponseEntity.ok(repo.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
