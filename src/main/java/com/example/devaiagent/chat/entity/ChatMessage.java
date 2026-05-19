package com.example.devaiagent.chat.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "chat_messages")
public class ChatMessage extends BaseEntity {
    public Long conversationId;
    public String senderType;
    public String content;
    public Instant sentAt;
}

