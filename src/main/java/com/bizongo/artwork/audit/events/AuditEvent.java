package com.bizongo.artwork.audit.events;

import com.bizongo.artwork.audit.entities.AccountActivities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditEvent {
    private AccountActivities accountActivities;
}
