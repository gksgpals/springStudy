package hello.example.hello_spring.controller;

public class MemberForm {
    private String name;
    // createMemberForm.html에서 입력 값인 name과 같기 때문에 자동으로 매칭됨.
    // 즉, 사용자가 입력한 이름을 저장하는 변수

    public String getName() {
        // 외부에서 name값을 읽을 때 사용
        return name;
    }

    public void setName(String name) {
        // 외부에서 name값을 저장할 때 사용
        this.name = name;
    }
}
