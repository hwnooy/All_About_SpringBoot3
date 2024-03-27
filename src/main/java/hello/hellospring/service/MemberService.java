package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
//    private final MemberRepository memberRepository
//            = new MemoryMemberRepository();

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){
        // 외부에서 넣어주도록 바꿔줌
        this.memberRepository = memberRepository;
    }
    // 회원가입, 룰은 동명이인 안됨
    public Long join(Member member){
        //같은 이름의 중복회원X, optional 감싸면 옵셔널 안에 멤버
        // command option v인가 b 옵션
        // orElseGet()을 많이 씀
        // Optional<Member> result = memberRepository.findByName(member.getName());
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /* 전체 회원 조회 - 비즈니스 처리가 서비스, 리포지토리는 단순 기계 => 네이밍도 적용*/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
