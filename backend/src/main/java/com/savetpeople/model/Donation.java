package com.savetpeople.model;

import javax.persistence.*;

@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long donorId; // reference to User
    private Long beneficiaryId; // reference to User
    private String items; // e.g., cesta básica details
    private String status; // requested, sent, delivered

    public Donation() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getDonorId() { return donorId; }
    public void setDonorId(Long donorId) { this.donorId = donorId; }
    public Long getBeneficiaryId() { return beneficiaryId; }
    public void setBeneficiaryId(Long beneficiaryId) { this.beneficiaryId = beneficiaryId; }
    public String getItems() { return items; }
    public void setItems(String items) { this.items = items; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
