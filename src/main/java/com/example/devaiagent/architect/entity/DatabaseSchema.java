package com.example.devaiagent.architect.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "database_schemas")
public class DatabaseSchema extends BaseEntity {
    public Long strategyId;
    public String schemaJson;
}

