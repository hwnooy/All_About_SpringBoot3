package hello.hellospring.order;

import hello.hellospring.discount.DiscountPolicy;
import hello.hellospring.discount.FixDiscountPolicy;
import hello.hellospring.discount.RateDiscountPolicy;
import hello.hellospring.member.Member;
import hello.hellospring.member.MemberRepository;
import hello.hellospring.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{


    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 인터페이스에만 의존하도록 수정
    // private DiscountPolicy discountPolicy; => nullPoiint 구체 객체가 없음
    // 그래서 이 인터페이스가 직접 구현객체를 선택할 수 있게 그 아래와 같이 설정한다.
    private DiscountPolicy discountPolicy;
    // private DiscountPolicy discountPolicy = new FixDiscountPolicy();

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
            Member member = memberRepository.findById(memberId);
            // 할인은 discountpolicy만 알고 단일체계 원칙이 잘 지켜진 케이스,
            // 할인정책에 멤버를 넘기고
            int discountPrice = discountPolicy.discount(member, itemPrice);
            // 최종 생성 주문을 반환
            return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
