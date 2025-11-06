package hello.example.hello_spring.repository;

import hello.example.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 회원 1명 저장
    Member save(Member member);

    // id로 찾기. 없을 수도 있으므로 Optional로 감싸기.
    Optional<Member> findById(Long id);

    // 이름으로 찾기. 없을 수도 있으므로 Optional로 감싸기.
    Optional<Member> findByName(String name);

    // 전부 가져오기
    List<Member> findAll();
}
