package hello.hellospring.service;

import hello.example.hello_spring.domain.Member;
import hello.example.hello_spring.repository.MemoryMemberRepository;
import hello.example.hello_spring.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 각 테스트 실행 전에 자동으로 실행됨.
    public void beforeEach(){
        // 새 저장소 객체 하나 생성.
        memberRepository = new MemoryMemberRepository();

        // 위 저장소를 주입해서 서비스 객체 생성.
        memberService = new MemberService(memberRepository);
    }

    // 각 테스트가 끝날 때마다 저장소 초기화.
    // 이전 테스트 데이터가 다음 테스트에 영향을 주지 않게 비워줌.
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();;
    }

    @Test
    void 회원가입() {
        // given 테스트 준비 단계
        Member member = new Member();
        member.setName("hello");

        // when 테스트 실행 단계

        // join()을 호출하여 회원가입 진행
        // 결과로 saveId(회원 Id)가 반환됨
        Long saveId = memberService.join(member);

        // then 검증 단계

        // findOne(saveId)로 가입된 회원을 다시 조회
        // Optional에서 .get()으로 실제 객체를 꺼냄
        Member findMember = memberService.findOne(saveId).get();

        // assertThat(...).isEqualTo(...)으 저장할 때 이름이랑 조회된 이름이 같은지 비교
        // 같으면 테스트 성공
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외(){
        // given

        // 이름이 똑같은 회원 두명을 준비
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when

        // 첫번째 회원은 정상적으로 가입됨
        memberService.join(member1);

        // 두번째 회원을 가입시키려면 IllegalStateException이 발생해야됨
        // .class를 붙이면 IllegalStateException이라는 클래스의 메타정보를 담은 Class 객체를 의미하게 됨
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}