package com.example.devaiagent.developer.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "debug_logs")
public class DebugLog extends BaseEntity {
    public Long projectId;
    public Long generationJobId;
    public String errorLog;
    public String fixSummary;
}

