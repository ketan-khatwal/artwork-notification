package com.bizongo.artwork.audit.repositories;

import com.bizongo.artwork.audit.entities.AccountActivities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AccountActivitiesRepository extends JpaRepository<AccountActivities, Long> {
    Optional<List<AccountActivities>> findByUserId(int userId);
}
