package com.savetpeople.repository;

import com.savetpeople.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByBeneficiaryId(Long beneficiaryId);
    List<Donation> findByDonorId(Long donorId);
    List<Donation> findByOngId(Long ongId);
    List<Donation> findByStatus(String status);
}
