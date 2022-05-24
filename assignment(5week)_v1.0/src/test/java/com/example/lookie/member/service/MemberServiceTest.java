package com.example.lookie.member.service;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class MemberServiceTest {

    MemberService memberService;

    @MockBean MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    public void 회원가입(){
        /**
         * given
         */
        Member AdminMember = Member.createAdminMember("dpdnjs0125@gmail.com", "1q2w3e4r", "kim", new Address("인천", "송도", "21212"));
        Member UserMember = Member.createUserMember("yeahxne@gmail.com", "1q2w3e4r!", "lee", new Address("서울", "종로", "13451"));

        //when: 간단히 Mock 객체의 행동을 설정("어떤 동작을 할때~"라는 명세만 주어짐)
        //thenReturn: 단순한 값으로 반환("어떤 것을 한다"라는 명세를 주는 함수)
        Mockito.when(memberRepository.save(AdminMember)).thenReturn(AdminMember);
        Mockito.when(memberRepository.save(UserMember)).thenReturn(UserMember);

        /**
         * when
         */
        Long AdminMemberId = memberService.joinAdmin(AdminMember.getEmail(), AdminMember.getPassword(), AdminMember.getName(), AdminMember.getAddress());
        Long UserMemberId = memberService.joinUser(UserMember.getEmail(), UserMember.getPassword(), UserMember.getName(), UserMember.getAddress());

        /**
         * then
         */
        assertThat(AdminMemberId).isEqualTo(AdminMember.getId());
        assertThat(UserMemberId).isEqualTo(UserMember.getId());
    }
}
