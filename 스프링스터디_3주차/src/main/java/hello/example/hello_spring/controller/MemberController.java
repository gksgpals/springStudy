package hello.example.hello_spring.controller;

import hello.example.hello_spring.domain.Member;
import hello.example.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    // 회원 등록 기능을 담당하는 스프링 MVC 컨트롤러
    // 즉, 회원가입 페이지를 보여주고 입력받은 회원 정보를 저장

    private final MemberService memberService;

    @Autowired
    // memberController는 MemberService 없이는 동작할 수 없음.
    // 그래서 MemberService 객체 의존성 주입.
    // 앞으로 MemberService memberService = new MemberService(); 안해도됨.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        // 단순히 회원가입 폼 보여줌.
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    // 사용자가 회원가입 폼에서 "등록" 버튼을 누르면 POST 방식으로 /members/new로 요청이 옴.
    // 이 메서드가 그 요청을 처리
    public String createMemberForm(MemberForm form){
        // 폼에서 입력된 데이터(name)을 자동으로 form 객체에 담아둠.

        Member member = new Member();
        // 새 회원 객체 생성.

        member.setName(form.getName());
        // 입력받은 이름을 member에 넣어둠.

        memberService.join(member);
        // 비즈니스 로직을 담당하는 서비스 계층 호출.
        // join() 메서드 안에서는 중복 회원 체크 > 저장소의 저장 등의 로직이 있었음.

        return "redirect:/";
        // 회원가입이 끝나면 홈 화면("/")으로 리다이렉트함.
    }

    @GetMapping("/members")
    // 사용자가 웹 브라우저에 "http://localhost:8080/members" 요청하면 스프링이 이 메서드를 실행

    public String list(Model model){
        // 회원 전체 목록을 조회

        List<Member> members = memberService.findMembers();
        // memberService는 비즈니스 로직 담당 클래스
        // findMembers()는 내부적으로 저장소(Repository)에서 모든 회원 데이터를 가져옴.
        // Member 객체들이 담겨있음.

        model.addAttribute("members", members);
        // 뷰에 데이터를 전달.
        // model은 컨트롤러와 뷰 사이의 다리 역할 (model 객체 안에 데이터를 넣으면, 뷰에서 꺼내 쓸 수 있음).

        return "members/memberList";
        // model에 담은 데이터를 memberList.html 뷰에 전달.
        // 스프링은 그 데이터를 이용해 HTML을 렌더링해서 최종적으로 브라우저에 보여줌.
    }
}
