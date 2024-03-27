package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    // 저장을 하기 위한 메모리 공간 변수
    private static Map<Long, Member> store
            = new HashMap<>();

    private static long sequence = 0L;


    // store에 넣기전 id를 설정하고 store에 저장하고 map에 저장되면 결과를 반한
    // 일단 repository에 넣는게 아닌 초보의 관점이네!
    @Override
    public Member save(Member member) {
        // 기존 sequence에서 1 증가된 id값으로 세팅
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
        // 싹 비우는 코드
    }
}
