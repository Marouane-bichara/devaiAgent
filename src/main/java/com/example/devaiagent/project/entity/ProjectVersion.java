package com.example.devaiagent.project.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_versions")
public class ProjectVersion extends BaseEntity {
    public Long projectId;
    public String versionLabel;
    public String notes;
}

