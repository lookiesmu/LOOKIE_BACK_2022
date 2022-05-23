package com.example.lookie.group.domain.Repository;

import com.example.lookie.group.domain.domain.Group;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class GroupRepository1Test {
    @Autowired
    GroupRepository1 groupRepository1;

    @Test
    @DisplayName("그룹 만들기")
    void createGroup() {
        //given
        Group group1 = Group.createGroup("a","b","ccd");

        //when
        Group result1 = groupRepository1.save(group1);

        //then
        Assertions.assertThat(result1.getOwnerEmail()).isEqualTo(group1.getOwnerEmail());
    }

    @Test
    @DisplayName("Group의 리스트를 반환 하는지 확인")
    void GroupList() {
        //given
        Group group1 = Group.createGroup("a","b","ccd");
        Group group2 = Group.createGroup("e","ff","ggg");
        groupRepository1.save(group1);
        groupRepository1.save(group2);

        //when
        List<Group> result = groupRepository1.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}