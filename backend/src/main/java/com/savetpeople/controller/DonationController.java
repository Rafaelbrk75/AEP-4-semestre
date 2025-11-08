package com.savetpeople.controller;

import com.savetpeople.model.Donation;
import com.savetpeople.repository.DonationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/donations")
public class DonationController {
    private final DonationRepository repo;

    public DonationController(DonationRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Donation> all() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Donation> create(@Valid @RequestBody Donation donation) {
        if (donation.getStatus() == null) {
            donation.setStatus("requested");
        }
        Donation saved = repo.save(donation);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/beneficiary/{beneficiaryId}")
    public List<Donation> getByBeneficiary(@PathVariable Long beneficiaryId) {
        return repo.findByBeneficiaryId(beneficiaryId);
    }

    @GetMapping("/donor/{donorId}")
    public List<Donation> getByDonor(@PathVariable Long donorId) {
        return repo.findByDonorId(donorId);
    }

    @GetMapping("/ong/{ongId}")
    public List<Donation> getByOng(@PathVariable Long ongId) {
        return repo.findByOngId(ongId);
    }

    @GetMapping("/status/{status}")
    public List<Donation> getByStatus(@PathVariable String status) {
        return repo.findByStatus(status);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Donation> approve(@PathVariable Long id) {
        return repo.findById(id)
                .map(donation -> {
                    donation.setStatus("approved");
                    donation.setApprovedAt(LocalDateTime.now());
                    return ResponseEntity.ok(repo.save(donation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/send")
    public ResponseEntity<Donation> send(
            @PathVariable Long id,
            @RequestBody(required = false) String trackingCode) {
        return repo.findById(id)
                .map(donation -> {
                    donation.setStatus("sent");
                    donation.setSentAt(LocalDateTime.now());
                    if (trackingCode != null && !trackingCode.isEmpty()) {
                        donation.setTrackingCode(trackingCode);
                    } else {
                        donation.setTrackingCode("TRACK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                    }
                    return ResponseEntity.ok(repo.save(donation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/deliver")
    public ResponseEntity<Donation> deliver(@PathVariable Long id) {
        return repo.findById(id)
                .map(donation -> {
                    donation.setStatus("delivered");
                    donation.setDeliveredAt(LocalDateTime.now());
                    return ResponseEntity.ok(repo.save(donation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donation> update(@PathVariable Long id, @RequestBody Donation donation) {
        return repo.findById(id)
                .map(existing -> {
                    if (donation.getItems() != null) existing.setItems(donation.getItems());
                    if (donation.getStatus() != null) existing.setStatus(donation.getStatus());
                    if (donation.getDeliveryAddress() != null) existing.setDeliveryAddress(donation.getDeliveryAddress());
                    if (donation.getTrackingCode() != null) existing.setTrackingCode(donation.getTrackingCode());
                    if (donation.getBeneficiaryId() != null) existing.setBeneficiaryId(donation.getBeneficiaryId());
                    if (donation.getDonorId() != null) existing.setDonorId(donation.getDonorId());
                    if (donation.getOngId() != null) existing.setOngId(donation.getOngId());
                    return ResponseEntity.ok(repo.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
