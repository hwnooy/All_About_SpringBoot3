package hello.hellospring.member;

public class MemberServiceImpl implements MemberService{



    private final MemberRepository memberRepository;
    //= new MemoryMemberRepository(); app config를 위해 지워주기
    // cmd+shift+enter => 세미콜론까지 완성을 해준다.

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
