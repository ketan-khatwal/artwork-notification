package com.bizongo.artwork.audit.services;

import com.bizongo.artwork.audit.entities.AccountActivities;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AuditLogsService {
    AccountActivities save(AccountActivities accountActivities);
    Optional<List<AccountActivities>> findByUserId(int userId);
}
