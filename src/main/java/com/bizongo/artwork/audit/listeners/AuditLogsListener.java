package com.bizongo.artwork.audit.listeners;

import com.bizongo.artwork.audit.events.AuditEvent;
import com.bizongo.artwork.audit.services.AuditLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuditLogsListener {

    @Autowired
    private AuditLogsService service;

    @EventListener
    public void createAuditLog(AuditEvent event) {
        service.save(event.getAccountActivities());
    }

}
