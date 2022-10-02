package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    // clear 하기 위해서 Repository 가져오자
    MemoryMemberRepository memberRepository;

    // 테스트 실행할 때 마다 먼저 memberRespository 생성하고 memberService 생성시 memberRepository를 인자로 주어 같은 memberRespository가 사용되도록 함
    // memberService 입장에서는 dependency Injection = DI 라고 한다
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        /**
         *  try {
         *             memberService.join(member2);
         *             fail();
         *         } catch (IllegalStateException e) {
         *             assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
         *         }
         */

        // 위의 경우 때문에 try catch문을 작성하는것이 애매
        // assertThrows를 사용하자 join 했을 때, IllegalStateException 예외가 터져야 한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //에러 메세지 비교
        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}