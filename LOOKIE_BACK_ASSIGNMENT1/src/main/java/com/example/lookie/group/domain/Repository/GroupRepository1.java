package com.example.lookie.group.domain.Repository;

import com.example.lookie.group.domain.domain.Group;
import com.example.lookie.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository1 extends JpaRepository<Group, Long> {
    Optional<Group> findByOwnerEmail(String OwnerEmail);
    Optional<Group> findByName(String Name);

    void deleteByOwnerEmail(String ownerEmail);
}
