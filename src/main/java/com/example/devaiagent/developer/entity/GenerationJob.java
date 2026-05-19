package com.example.devaiagent.developer.entity;

import com.example.devaiagent.common.base.BaseEntity;
import com.example.devaiagent.common.enums.AgentPhase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "generation_jobs")
public class GenerationJob extends BaseEntity {
    public Long projectId;
    public AgentPhase phase;
    public String status;
    public String prompt;
}

