package com.example.devaiagent.project.entity;

import com.example.devaiagent.common.base.BaseEntity;
import com.example.devaiagent.common.enums.ProjectStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {
    public String name;
    public String description;
    public ProjectStatus status;
    public BigDecimal price;
    public Long clientId;
    public String strategyJson;
    public String deliveryLink;
}

