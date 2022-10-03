package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 스프링 컨테이너가 뜨면 MemberController 객체를 생성 및 관리
public class MemberController {
    private final MemberService memberService;

    // MemberController 객체 생성할 때 이 생성자 호출
    @Autowired // 스프링컨테이너에서 memberService를 가져다 연결시켜줌 (의존관계 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
