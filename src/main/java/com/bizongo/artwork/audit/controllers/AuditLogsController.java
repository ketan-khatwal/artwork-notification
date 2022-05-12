package com.bizongo.artwork.audit.controllers;

import com.bizongo.artwork.audit.controllers.request.AuditLogsRequest;
import com.bizongo.artwork.audit.entities.AccountActivities;
import com.bizongo.artwork.audit.publishers.AuditLogsPublisher;
import com.bizongo.artwork.audit.services.AuditLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/audit")
public class AuditLogsController {

    @Autowired
    AuditLogsService auditLogsService;

    @Autowired
    AuditLogsPublisher auditLogsPublisher;

    @PostMapping("/log")
    public void addEvent(@RequestBody AuditLogsRequest request) {
        auditLogsPublisher.publishAuditCreateEvent(request);
    }

    @GetMapping("/log")
    public ResponseEntity<List<AccountActivities>> getAuditLogs(@RequestParam("user_id") int userId) {
        Optional<List<AccountActivities>> accountActivities = auditLogsService.findByUserId(userId);
        return new ResponseEntity<>(accountActivities.get(), HttpStatus.OK);
    }
}
