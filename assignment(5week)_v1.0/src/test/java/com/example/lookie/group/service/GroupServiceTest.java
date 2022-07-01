package com.example.lookie.group.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class GroupServiceTest {

    GroupService groupService;

    @MockBean GroupRepository groupRepository;

    @BeforeEach
    void beforeEach() {
        groupService = new GroupServiceImpl(groupRepository);
    }

    void 그룹생성() {
        /**
         * given
         */
        Group group = Group.createGroup("동아리", "해당 동아리는 코딩 동아리입니다.", "dpdnjs0125@gmail.com");
        Mockito.when(groupRepository.save(group)).thenReturn(group);

        Long groupId = groupService.createGroup(group.getName(), group.getDescription(), group.getOwnerEmail());

        /**
         * when
         */
        Group saveGroup = groupRepository.findById(groupId).get();

        /**
         * then
         */
        assertThat(saveGroup.getName()).isEqualTo("동아리");
    }
}