package com.example.lookie.grouprequest.repository;

import com.example.lookie.group.domain.domain.Group;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupRequestRepository extends JpaRepository<GroupRequest, Long> {



}
