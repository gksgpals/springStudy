package hello.hellospring.repository;

// 이 테스트에서 사용할 클래스들을 import.
import hello.example.hello_spring.domain.Member; // 회원 객체
import hello.example.hello_spring.repository.MemberRepository; // 인터페이스
import hello.example.hello_spring.repository.MemoryMemberRepository; // 실제 구현체

// JUnit 테스트용 어노테이션
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

// assertThat을 클래스 이름 없이 바로 쓸 수 있게 static import
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// MemoryMemberRepositoryTest를 테스트할 거임
class MemoryMemberRepositoryTest {
    // 실제로 테스트할 저장소 객체를 만듦.
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 하나 끝낼 때마다 실행되는 코드.
    // 테스트끼리 데이터가 섞이지 않게, 저장소를 초기화하는 역할.
    @AfterEach
    public void afterEach(){
        repository.clearStore();;
    }

    // save() 기능을 Test할 거임.
    @Test
    public void save(){
        // 새 회원을 하나 만듦.
        Member member = new Member();
        member.setName("spring");

        // 이 시점에서 member에게 id가 자동으로 세팅됨.
        repository.save(member);

        // 방금 저장한 회원의 id를 이용해서 저장소에서 다시 찾아오고, Optional로 감싸져 있던 그 회원 객체를 꺼내서 result에 담음.
        // findById()는 Optional<member>를 반환하므로 .get()으로 진짜 Member를 꺼냄.
        Member result = repository.findById(member.getId()).get();

        // 저장했던 member랑, 꺼내온 result가 같은 객체인지 검사.
        // 성공하면 저장 기능이 제대로 동작
        Assertions.assertThat(member).isEqualTo(result);

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
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 저장된 회원 전부를 꺼내서 result에 담음.
        List<Member> result = repository.findAll();

        // 결과 리스트의 크기가 2명인지 확인.
        assertThat(result.size()).isEqualTo(2);
    }
}
