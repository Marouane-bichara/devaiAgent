package com.example.devaiagent.architect.entity;

import com.example.devaiagent.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "database_columns")
public class DatabaseColumn extends BaseEntity {
    public Long tableId;
    public String columnName;
    public String columnType;
    public Boolean nullable;
}

