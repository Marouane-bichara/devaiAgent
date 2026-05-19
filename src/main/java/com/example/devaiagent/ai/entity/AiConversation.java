package com.example.devaiagent.ai.entity;

import com.example.devaiagent.common.base.BaseEntity;
import com.example.devaiagent.common.enums.AiProviderType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ai_conversations")
public class AiConversation extends BaseEntity {
    public Long projectId;
    public AiProviderType providerType;
    public String title;
}

