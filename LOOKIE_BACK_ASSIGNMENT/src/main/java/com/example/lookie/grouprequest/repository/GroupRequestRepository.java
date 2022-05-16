package com.example.lookie.grouprequest.repository;

import com.example.lookie.group.domain.Group;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public interface GroupRequestRepository extends JpaRepository<GroupRequest,Long>{

}
