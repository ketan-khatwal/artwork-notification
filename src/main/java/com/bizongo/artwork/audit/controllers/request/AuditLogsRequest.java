package com.bizongo.artwork.audit.controllers.request;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;

public @Data class AuditLogsRequest {

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Integer userId;

    private String email;

    private Integer trackableId;

    private String trackableType;

    private Integer companyId;

    private String event;

    private Map<String, Object> oldValues;

    private Map<String, Object> newValues;

    private Map<String, Object> resources;

    private Map<String, Object> request;

    private Map<String, Object> additionalFilterableEntities;
}
