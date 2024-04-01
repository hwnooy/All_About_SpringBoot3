package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //@Autowired
    private final MemberService memberService;
    //컨테이너에 등록하면 하나만 하면 됨

}
