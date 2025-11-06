package hello.example.hello_spring.service;

import hello.example.hello_spring.domain.Member;
import hello.example.hello_spring.repository.MemberRepository;
import hello.example.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// 회원 관련 비즈니스 로직(업무 로직)을 담당하는 클래스.
public class MemberService {
    // private final : 외부에서 바꿀 수 없고, 오직 한 번만 세팅됨.
    // memberRepository : 실제 데이터 저장/조회 역할을 맡은 저장소 객체.
    // 즉 서비스는 회원 등록/조회 등의 큰 기능을 맡고, 실제 데이터 저장은 Repository에 맡김.
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // ** 같은 이름이 있는 중복 회원 안됨 **
        validateDuplicateMember(member);

        // 통과하면 회원을 저장소(Map)에 저장.
        memberRepository.save(member);

        // 저장된 회원의 id를 반환 -> 나중에 누가 가입했는지 구분하는 식별자 역할.
        return member.getId();
    }

    // 중복 회원 검증 로직
    private void validateDuplicateMember(Member member) {
        // 이름으로 찾아서 결과를 Optional<Member>로 받음.
        memberRepository.findByName(member.getName())

                // 만약 값이 존재하면 람다 안 코드 실행.
                .ifPresent(m -> {

                    // 강제로 에러 발생 (가입을 막음).
                    throw new IllegalStateException("이미 존재하는 회원입니다.");

                    // 즉, 같은 이름이 있으면 가입시키지 말고 예외 발생.
                });
    }


    /**
     * 전체 회원 조회
     */

    // 저장소에 있는 모든 회원 리스트를 그대로 반환.
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 저장소에 있는 특정 회원 1명을 조회.
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
