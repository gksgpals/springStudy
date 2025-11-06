package hello.example.hello_spring.domain;

// Member 클래스를 public(공개)으로 선언
public class Member {

    // 회원 아이디와 이름은 private로 캡슐화
    private Long id;
    private String name;

    // getter 및 setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}