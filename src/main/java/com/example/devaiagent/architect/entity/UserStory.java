package com.example.devaiagent.architect.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_stories")
public class UserStory extends BaseEntity {
    public Long strategyId;
    public String title;
    public String description;
    public String priority;
}

