package com.payment.repository;

import com.payment.enums.PaymentStatus;
import com.payment.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByOrderId(Long orderId);

    @Modifying
    @Query("update Payment p set p.status = :status where p.orderId = :orderId")
    Integer updatePaymentStatus(@Param("status") PaymentStatus status, @Param("orderId") Long orderId);

}
