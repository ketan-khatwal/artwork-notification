package com.bizongo.artwork.audit.publishers;

import com.bizongo.artwork.audit.controllers.request.AuditLogsRequest;
import com.bizongo.artwork.audit.entities.AccountActivities;
import com.bizongo.artwork.audit.events.AuditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AuditLogsPublisher {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void publishAuditCreateEvent(AuditLogsRequest request) {
        AuditEvent auditEvent = new AuditEvent();
        auditEvent.setAccountActivities(convertRequestToEntity(request));
        applicationEventPublisher.publishEvent(auditEvent);
    }

    private AccountActivities convertRequestToEntity(AuditLogsRequest request) {
        AccountActivities accountActivities = new AccountActivities();
        if (request.getUserId() != null)
            accountActivities.setUserId(request.getUserId());
        if (request.getEmail() != null)
            accountActivities.setEmail(request.getEmail());
        if (request.getTrackableId() != null)
            accountActivities.setTrackableId(request.getTrackableId());
        if (request.getTrackableType() != null)
            accountActivities.setTrackableType(request.getTrackableType());
        if (request.getCompanyId() != null)
            accountActivities.setCompanyId(request.getCompanyId());
        if (request.getEvent() != null)
            accountActivities.setEvent(request.getEvent());
        if (request.getOldValues() != null)
            accountActivities.setOldValues(request.getOldValues());
        if (request.getNewValues() != null)
            accountActivities.setNewValues(request.getNewValues());
        if (request.getResources() != null)
            accountActivities.setResources(request.getResources());
        if (request.getRequest() != null)
            accountActivities.setRequest(request.getRequest());
        if (request.getAdditionalFilterableEntities() != null)
            accountActivities.setAdditionalFilterableEntities(request.getAdditionalFilterableEntities());
        return accountActivities;
    }
}
