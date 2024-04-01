package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach(){
        // DI 개념
        // 테스트 실행할때마다 메모리멤버 리포지터리 만들고
        // 멤버서비스에 넣어줌, 같은 메모리멤버서비스를 사용할 수 있게
        // 멤버 서비스 기준에선 멤버 리포지토리를 주입해준것?
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    // 테스트와 기존의 클래스와 다름, 메모리멤버리포지토리가 새롭게 생성되면 내용도 달라질 수 도 있으니까 아래와 같이 바꾸기
    // 외부에서 넣어주도록

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {

        // given : 어떻게 주어졌을 때, 어떤 데이터를 기반으로??
        Member member = new Member();
        member.setName("spring");
        // when : 실행했을때
        Long saveId = memberService.join(member); // 리포에 저장된게 맞아?도 체크해야
        // then : 나와야 한다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


//        try{
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            // 기존의 예외에 걸려 실행되는 ifPresent()문으로
//             assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}