package com.example.devaiagent.delivery.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "download_links")
public class DownloadLink extends BaseEntity {
    public Long projectId;
    public Long paymentId;
    public String token;
    public Instant expiresAt;
    public Boolean used;
}

