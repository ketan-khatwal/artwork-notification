package com.bizongo.artwork.audit.services;

import com.bizongo.artwork.audit.entities.AccountActivities;
import com.bizongo.artwork.audit.repositories.AccountActivitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditLogsServiceImpl implements AuditLogsService{

    @Autowired
    AccountActivitiesRepository accountActivitiesRepository;

    public AccountActivities save(AccountActivities accountActivities) {
        return accountActivitiesRepository.save(accountActivities);
    }

    public Optional<List<AccountActivities>> findByUserId(int userId) {
        Optional<List<AccountActivities>> accountActivities = accountActivitiesRepository.findByUserId(userId);
        return accountActivitiesRepository.findByUserId(userId);
    }
}
