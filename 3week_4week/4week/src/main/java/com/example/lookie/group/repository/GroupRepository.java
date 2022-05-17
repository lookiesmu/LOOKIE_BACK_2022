package com.example.lookie.group.repository;

import com.example.lookie.group.domain.Group;

import com.example.lookie.group.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByName(String name);
    Optional<Group> findByOwnerEmail(String OwnerEmail);
    void deleteByName(String name);
}