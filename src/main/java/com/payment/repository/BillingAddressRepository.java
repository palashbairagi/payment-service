package com.payment.repository;

import com.payment.model.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {
}
