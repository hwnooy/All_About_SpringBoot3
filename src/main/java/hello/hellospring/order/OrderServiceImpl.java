package hello.hellospring.order;

import hello.hellospring.discount.DiscountPolicy;
import hello.hellospring.discount.FixDiscountPolicy;
import hello.hellospring.member.Member;
import hello.hellospring.member.MemberRepository;
import hello.hellospring.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{


    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
