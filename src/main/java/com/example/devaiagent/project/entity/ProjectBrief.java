package com.example.devaiagent.project.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_briefs")
public class ProjectBrief extends BaseEntity {
    public Long projectId;
    public String rawText;
    public String uploadedFileName;
}

