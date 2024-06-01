package hello.hellospring;

import hello.hellospring.member.MemberService;
import hello.hellospring.member.MemberServiceImpl;
import hello.hellospring.member.MemoryMemberRepository;

public class AppConfig {
    // memberService만들기 원래는 직접 멤버서비스 안에서 memberservice
    public MemberService memberService(){
        // 객체 생성 후 원래 memberserviceimple 이 했는데 appconfig에서 이제 수행함
        return new MemberServiceImpl(new MemoryMemberRepository());
    }


}
