package com.example.devaiagent.security.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken extends BaseEntity {
    public String token;
    public Instant expiresAt;
    public Boolean revoked;
    public Long userId;
}

