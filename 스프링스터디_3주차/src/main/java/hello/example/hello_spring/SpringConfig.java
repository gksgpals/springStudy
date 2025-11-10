package hello.example.hello_spring;

import hello.example.hello_spring.repository.MemberRepository;
import hello.example.hello_spring.repository.MemoryMemberRepository;
import hello.example.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 이 클래스는 "스프링 설정 파일이다:를 의미함.
// 즉, 스프링이 시작할 때 이 파일을 읽고 안에 있는 @Bean 메서드들을 실행해서 그 리턴값을 스프링 컨테이너에 빈(Bean)으로 등록한다.
public class SpringConfig {

    @Bean
    // 이 메서드가 리턴하는 객체를 스프링이 관리하는 빈으로 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
