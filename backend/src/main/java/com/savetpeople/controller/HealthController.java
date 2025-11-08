package com.savetpeople.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "online");
        response.put("message", "SaveToPeople API is running");
        response.put("version", "1.0.0");
        response.put("endpoints", Map.of(
            "auth", "/api/auth/login, /api/auth/register",
            "ongs", "/api/ongs",
            "jobs", "/api/jobs",
            "donations", "/api/donations",
            "users", "/api/users",
            "applications", "/api/applications"
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api")
    public ResponseEntity<Map<String, Object>> api() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "online");
        response.put("message", "SaveToPeople API");
        response.put("endpoints", Map.of(
            "auth", "/api/auth",
            "ongs", "/api/ongs",
            "jobs", "/api/jobs",
            "donations", "/api/donations",
            "users", "/api/users",
            "applications", "/api/applications"
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        return ResponseEntity.ok(response);
    }
}

