package hello.example.hello_spring.repository;

// Member 클래스 가져다쓰겠음
import hello.example.hello_spring.domain.Member;

// Map, HashMap, Optional, List, ArrayList 같은 유틸 컬렉션/클래스들을 한번에 import.
import java.util.*;

// MemberRepository 인터페이스를 구현할 거임
public class MemoryMemberRepository implements MemberRepository {
    // 회원을 저장할 메모리 저장소
    // HachMap 객체 하나를 새로 만들겠음
    // HashMap이란 키-값을 보관하는 자료구조임. 키로 바로 찾는 거라 삽입/조회 평균은 O(1)임.
    // static >> 이 클래스로 만든 모든 인스턴스가 저장소를 공유.
    private static Map<Long, Member> store = new HashMap<>();

    // 자동 증가 id
    private static long sequence = 0L; // 0,1,2 ... 키 값을 생성해줌

    // 아래 메서드가 인터페이스의 메서드를 재정의(구현)한다는 표시.
    @Override
    public Member save(Member member) {
        // id 세팅
        member.setId(++sequence);

        // 맵에 키,값 저장
        store.put(member.getId(), member);

        // id가 채워진 member 객체 반환
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 값이 있으면 Optional.of(값), 없으면 Optional.empty()가 되도록 ofNullable로 감싸기.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
      // 맵에 저장된 모든 Member 값들을 꺼내서 스트림으로 순회 시작
        return store.values().stream()

                // 각 member의 name이 인자로 받은 name과 같은 것만 골라냄
                .filter(member -> member.getName().equals(name))

                // 조건에 맞는 것 중 아무거나 하나를 Oprional로 반환
                .findAny();
    }

    @Override
    // 저장된 모든 회원 목록을 반환하는 메서드
    public List<Member> findAll() {
        // 맵의 값 컬렉션을 새 ArrayList로 복사해 반환.
        // 이렇게 하면 내부 컬렉션을 그대로 노출하지 않아 바깥에서 마음대로 수정해도 원본 store가 안 망가짐.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
