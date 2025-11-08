package com.savetpeople.controller;

import com.savetpeople.model.Application;
import com.savetpeople.repository.ApplicationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationRepository repo;

    public ApplicationController(ApplicationRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Application> all() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Application> create(@Valid @RequestBody Application application) {
        Application saved = repo.save(application);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/job/{jobId}")
    public List<Application> getByJob(@PathVariable Long jobId) {
        return repo.findByJobId(jobId);
    }

    @GetMapping("/user/{userId}")
    public List<Application> getByUser(@PathVariable Long userId) {
        return repo.findByUserId(userId);
    }

    @GetMapping("/status/{status}")
    public List<Application> getByStatus(@PathVariable String status) {
        return repo.findByStatus(status);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Application> updateStatus(
            @PathVariable Long id,
            @RequestBody String status) {
        return repo.findById(id)
                .map(app -> {
                    app.setStatus(status);
                    return ResponseEntity.ok(repo.save(app));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

