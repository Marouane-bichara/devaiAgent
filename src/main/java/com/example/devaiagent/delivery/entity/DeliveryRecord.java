package com.example.devaiagent.delivery.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "delivery_records")
public class DeliveryRecord extends BaseEntity {
    public Long projectId;
    public String method;
    public Instant deliveredAt;
}

