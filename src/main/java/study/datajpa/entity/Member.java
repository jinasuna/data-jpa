package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"}) // 여기서 team이런거 입력하면 큰일난다. 연관관계 타고들어가서 다출력함! -> 무한루프 될 수 있음
public class Member {
    /**lesson1*/
//    @Id @GeneratedValue
//    private Long id;
//    private String username;
//
//    protected Member() { // 아무데서나 호출되지 않도록 하기위해!
//    }
//
//    public Member(String username) {
//        this.username = username;
//    }
    /**lesson2 예제 모델을 만들어 보자*/
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long Id;
    private String username;
    private int age;

    // Team과 Member 사이에 서로 연관관계를 맵핑하는 메소드를 만들어 줘야 한다.
    @ManyToOne // 회원은 팀 하나에만 소속될 수 있다. (이쪽에서 Team의 키 중 하나를 외래키로 가져온다)
    @JoinColumn(name = "team_id") // 이게 외래키 이름이 된다.
    private Team team;

    /** JPA는 기본적으로 디폴트 생성자가 필요한데, 접근 제어자가 protected까지만 가능하고 private을 사용하면 안된다.*/
//    protected Member(){ // lombok 떡칠로 해결함!
//    }
    public Member(String username){
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        // 파라미터로 넘어오는 team이 null일 경우 -> 예외를 터뜨리거나 무시하거나
        if(team!=null) {
            changeTeam(team);
        } // 무시했음

    }

    // 멤버는 내가 속한 팀을 변경할 수 있다. -> Team에 가서도 세팅 해주기 연관관계를 한번에 세팅?
    public void changeTeam(Team team){
        this.team = team; // 객체이기 때문에 내 Team만 바꿔주는게 아니라 반대쪽의 Member도 바꿔줘야 한다.
        team.getMembers().add(this); // 와 이걸 이걸로 이렇게 바꾸네!! 쌉편함
    }

}
