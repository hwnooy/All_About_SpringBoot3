package hello.hellospring;

import hello.hellospring.discount.DiscountPolicy;
import hello.hellospring.discount.FixDiscountPolicy;
import hello.hellospring.member.*;
import hello.hellospring.order.OrderService;
import hello.hellospring.order.OrderServiceImpl;

public class AppConfig {


    // memberService만들기 원래는 직접 멤버서비스 안에서 memberservice
    public MemberService memberService(){
        // memberServiceImple은 private final MemberRepository memberRepository;의 객체를 만든다.
        // appconfig는 memoryMemberRepository를 생성하고 그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달
        // 객체 생성 후 원래 memberserviceimple 이 했는데 appconfig에서 이제 수행함
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


    public OrderService orderService() {
        // 실제 객체를 여기서 주입!!
        return new OrderServiceImpl(memberRepository(),
                //new FixDiscountPolicy()
                discountPolicy());
    }

    // 이 함수를 orderService()에서 콜해서 fixdiscountpolicy를 사용하는것
    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}
