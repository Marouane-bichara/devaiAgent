package com.example.devaiagent.ai.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "token_usages")
public class TokenUsage extends BaseEntity {
    public Long projectId;
    public Long aiLogId;
    public Integer inputTokens;
    public Integer outputTokens;
    public Integer totalTokens;
}

