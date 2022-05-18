package com.example.lookie.group.domain.repository;

import com.example.lookie.group.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByGroupName(String name);
    Optional<Group> findByOwnerEmail(String ownerEmail);
    void deleteByGroupName(String name);

}

