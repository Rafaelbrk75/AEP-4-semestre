package com.savetpeople.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Job ID is required")
    @Column(name = "job_id")
    private Long jobId;

    @NotNull(message = "User ID is required")
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    private String phone;
    private String countryOfOrigin;
    private String experience;
    private String portugueseLevel;
    private String skills;
    private String motivation;

    @Column(name = "has_documentation")
    private Boolean hasDocumentation = false;

    @Column(name = "status")
    private String status = "pending"; // pending, approved, rejected

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "application_number")
    private String applicationNumber;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (applicationNumber == null) {
            applicationNumber = "APP-" + System.currentTimeMillis();
        }
    }

    public Application() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCountryOfOrigin() { return countryOfOrigin; }
    public void setCountryOfOrigin(String countryOfOrigin) { this.countryOfOrigin = countryOfOrigin; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    public String getPortugueseLevel() { return portugueseLevel; }
    public void setPortugueseLevel(String portugueseLevel) { this.portugueseLevel = portugueseLevel; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getMotivation() { return motivation; }
    public void setMotivation(String motivation) { this.motivation = motivation; }

    public Boolean getHasDocumentation() { return hasDocumentation; }
    public void setHasDocumentation(Boolean hasDocumentation) { this.hasDocumentation = hasDocumentation; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getApplicationNumber() { return applicationNumber; }
    public void setApplicationNumber(String applicationNumber) { this.applicationNumber = applicationNumber; }
}

