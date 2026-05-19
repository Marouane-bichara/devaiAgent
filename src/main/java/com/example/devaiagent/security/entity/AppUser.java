package com.example.devaiagent.security.entity;

import com.example.devaiagent.common.base.BaseEntity;
import com.example.devaiagent.common.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class AppUser extends BaseEntity {
    public String fullName;
    public String email;
    public String password;
    public BigDecimal balance;
    public Boolean enabled;
    public UserRole role;
}

