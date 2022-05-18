package com.example.lookie.grouprequest.repository;

import com.example.lookie.grouprequest.domain.GroupRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRequestRepository extends JpaRepository<GroupRequest, Long> {
    Optional<GroupRequest> findByOwnerEmail(String OwnerEmail);
}
