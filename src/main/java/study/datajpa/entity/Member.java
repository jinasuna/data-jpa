package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String username;

    protected Member() { // 아무데서나 호출되지 않도록 하기위해!
    }

    public Member(String username) {
        this.username = username;
    }
}
