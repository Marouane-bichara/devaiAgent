package com.example.devaiagent.architect.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "api_endpoints")
public class ApiEndpoint extends BaseEntity {
    public Long strategyId;
    public String path;
    public String httpMethod;
    public String description;
}

