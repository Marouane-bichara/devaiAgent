package com.example.devaiagent.payment.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_webhook_events")
public class PaymentWebhookEvent extends BaseEntity {
    public Long paymentId;
    public String provider;
    public String eventType;
    public String payload;
    public Boolean processed;
}

