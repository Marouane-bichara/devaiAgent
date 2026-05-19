package com.example.devaiagent.payment.entity;

import com.example.devaiagent.common.base.BaseEntity;
import com.example.devaiagent.common.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {
    public Long projectId;
    public Long userId;
    public String provider;
    public PaymentStatus status;
    public BigDecimal amount;
    public String currency;
    public String transactionReference;
}

