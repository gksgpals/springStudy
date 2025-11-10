package hello.example.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller// 이 클래스는 스프링이 관리하는 객체.
// 웹 요청을 받아서 어떤 화면을 보여줄지 결정.
// 즉, 누군가의 브라우저에서 /(홈 주소)를 요청하면, home.html 페이지를 보여줌.
public class HomeController {
    @GetMapping("/")
    // 만약 사용자가 브라우저에서 "http://localhost:8080/"을 입력하면, 스프링이 이 메서드(home())를 실행해준다.
    public String home() {
        return "home";
        // "resources/templates/home.html" 파일 찾아서 보여줌.
        // 기본적으로 스프링은 Thymeleaf 템플릿 엔진을 사용해서 HTML을 렌더링.
    }
}
