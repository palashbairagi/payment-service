package com.payment.model.entity;


import com.payment.constants.Constants;
import com.payment.enums.PaymentMethod;
import com.payment.enums.PaymentStatus;
import com.payment.model.common.BaseModel;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = Constants.TBL_PAYMENT_INFO)
@Data
public class Payment extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "confirmation_number")
    private Long confirmationNumber;

    @Column(name = "payment_date", columnDefinition = "DATE")
    private LocalDate paymentDate;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.CONFIRMED;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private BillingAddress billingAddress;
}
