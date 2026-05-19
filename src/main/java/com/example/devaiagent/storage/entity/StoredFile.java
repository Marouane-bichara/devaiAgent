package com.example.devaiagent.storage.entity;

import com.example.devaiagent.common.base.BaseEntity;
import com.example.devaiagent.common.enums.FileStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "stored_files")
public class StoredFile extends BaseEntity {
    public Long projectId;
    public String originalName;
    public String storedName;
    public String path;
    public String mimeType;
    public Long size;
    public FileStatus status;
}

