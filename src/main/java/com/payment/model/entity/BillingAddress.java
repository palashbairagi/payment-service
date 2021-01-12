package com.payment.model.entity;

import com.payment.constants.Constants;
import com.payment.model.common.BaseModel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Constants.TBL_BILLING_ADDRESS_INFO)
@Data
public class BillingAddress extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "line1")
    private String line1;

    @Column(name = "line2")
    private String line2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

}
