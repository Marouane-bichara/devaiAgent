package com.example.devaiagent.architect.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "database_tables")
public class DatabaseTable extends BaseEntity {
    public Long schemaId;
    public String tableName;
}

