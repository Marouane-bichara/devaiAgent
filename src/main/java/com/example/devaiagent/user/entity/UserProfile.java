package com.example.devaiagent.user.entity;

import com.example.devaiagent.common.base.BaseEntity;
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")
public class UserProfile extends BaseEntity {
    public Long userId;
    public String bio;
    public String companyName;
    public BigDecimal balance;
}

