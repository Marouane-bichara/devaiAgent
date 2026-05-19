package com.example.devaiagent.chat.entity;

import com.example.devaiagent.common.base.BaseEntity;
import com.example.devaiagent.common.enums.ConversationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_conversations")
public class ChatConversation extends BaseEntity {
    public Long projectId;
    public Long clientId;
    public ConversationStatus status;
}

