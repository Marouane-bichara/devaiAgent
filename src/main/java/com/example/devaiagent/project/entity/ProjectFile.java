package com.example.devaiagent.project.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_files")
public class ProjectFile extends BaseEntity {
    public Long projectId;
    public String fileName;
    public String filePath;
    public String fileType;
    public Boolean readOnly;
}

