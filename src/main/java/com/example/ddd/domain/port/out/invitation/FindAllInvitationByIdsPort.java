package com.example.ddd.domain.port.out.invitation;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;

import java.util.Collection;

public interface FindAllInvitationByIdsPort {
    Collection<Invitation> findAllByIds(Collection<Guid> ids);
}
