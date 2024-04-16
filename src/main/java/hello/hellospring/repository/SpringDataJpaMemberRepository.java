package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
    // 구현할게 없다!!
    // 이걸 쓰려면 어떻게 하나
    // findByName -> JPQL을 select m from Member where m.name = ? 으로 쿼리를 짜줌
    // 인터페이스 메서드 이름만으로 조회하는 기능을 제공해줌, 페이징 기능까지
}
