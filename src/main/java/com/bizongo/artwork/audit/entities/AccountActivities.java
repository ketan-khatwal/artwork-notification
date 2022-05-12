package com.bizongo.artwork.audit.entities;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Map;

@Entity
@ToString
@Getter
@Setter
@Table(name = "account_activities")
@TypeDef(name = "json", typeClass = JsonType.class)
public class AccountActivities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "email")
    private String email;

    @Column(name = "trackable_id")
    private int trackableId;

    @Column(name = "trackable_type")
    private String trackableType;

    @Column(name = "company_id")
    private int companyId;

    @Column(name = "event")
    private String event;

    @Column(name = "old_values", columnDefinition = "json")
    @Type(type = "json")
    private Map<String, Object> oldValues;

    @Column(name = "new_values", columnDefinition = "json")
    @Type(type = "json")
    private Map<String, Object> newValues;

    @Column(name = "resources", columnDefinition = "json")
    @Type(type = "json")
    private Map<String, Object> resources;

    @Column(name = "request", columnDefinition = "json")
    @Type(type = "json")
    private Map<String, Object> request;

    @Column(name = "additional_filterable_entities", columnDefinition = "json")
    @Type(type = "json")
    private Map<String, Object> additionalFilterableEntities;
}
