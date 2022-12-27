package hello.memberservice.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB 가 Id 를 자동으로 생성해줌 -> IDENTITY
     private Long id;

    //@Column(name = "username") //DB의 name 에 해당하는 컬럼 이름을 username 으로 설정
    private String name;

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
