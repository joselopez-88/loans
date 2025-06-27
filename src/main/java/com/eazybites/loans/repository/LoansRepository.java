package com.eazybites.loans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazybites.loans.model.entity.Loans;

public interface LoansRepository extends JpaRepository<Loans, Long> {
    Optional<Loans> findByMobileNumber(String mobileNumber);
    void deleteByMobileNumber(String mobileNumber);

}
