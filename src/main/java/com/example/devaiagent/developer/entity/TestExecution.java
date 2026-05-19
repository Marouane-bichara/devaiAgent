package com.example.devaiagent.developer.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_executions")
public class TestExecution extends BaseEntity {
    public Long projectId;
    public String status;
    public String testLog;
}

