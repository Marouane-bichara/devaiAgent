package com.example.devaiagent.ai.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ai_logs")
public class AiLog extends BaseEntity {
    public Long projectId;
    public Long conversationId;
    public String phase;
    public String prompt;
    public String response;
    public String errorLog;
    public Integer inputTokens;
    public Integer outputTokens;
}

