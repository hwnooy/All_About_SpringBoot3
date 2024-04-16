package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    // Test할 클래스 객체 만들기
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    // 테스트 메서드 끝날 때마다 실행되는 콜백함수 : 순서가 상관없이 실행 잘됨
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        // 내가 만든 데이터
        Member member = new Member();
        member.setName("spring");

        // 저장할때 id 세팅 후 저장
        repository.save(member);

        //optional이면 get()으로 바로 뽑을 수 있다. test론 괜찮
        // db에서 꺼낸 데이터
        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result==member));
        //Assertions.assertEquals(member, result);
        //org.junit.jupiter.api.Assertions.assertEquals(member, null);

        //org assertj
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findALl(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}



