package com.example.devaiagent.delivery.entity;

import com.example.devaiagent.common.base.BaseEntity;
import com.example.devaiagent.common.enums.FileStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_artifacts")
public class DeliveryArtifact extends BaseEntity {
    public Long projectId;
    public String artifactType;
    public String filePath;
    public FileStatus status;
}

