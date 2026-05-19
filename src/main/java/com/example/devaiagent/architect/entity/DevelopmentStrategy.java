package com.example.devaiagent.architect.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "development_strategies")
public class DevelopmentStrategy extends BaseEntity {
    public Long projectId;
    public String strategyJson;
    public Boolean approved;
    public Integer version;
}

