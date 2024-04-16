package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean : 컴포넌트로 적용해서 이거 빠져야함
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

    //private final DataSource dataSource;
    //private EntityManager em;
    // 데이터 소스 만들고 주입해줌

//    @Autowired
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        //this.dataSource = dataSource;
//        this.em = em;
//    }

  //스프링 데이터 JPA가 SpringDataJpaMemberRepository를 스프링빈으로 자동등록해줌
//    @Bean
//    public MemberRepository memberRepository() {
//        //return new MemoryMemberRepository();    }
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        //return new JpaMemberRepository(em);
//    }

}
