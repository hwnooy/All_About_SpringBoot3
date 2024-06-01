package hello.hellospring;

import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;
import hello.hellospring.member.MemberService;
import hello.hellospring.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl();
        // cmd +alt + v : new한 객체를 Member memberA를 앞에 만들어줌
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());

    }
}
