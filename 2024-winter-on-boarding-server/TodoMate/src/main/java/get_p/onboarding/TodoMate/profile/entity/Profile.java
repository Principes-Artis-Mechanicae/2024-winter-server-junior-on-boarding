package get_p.onboarding.TodoMate.profile.entity;

import get_p.onboarding.TodoMate.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    private String name;
    private String introduction;  //50글자 제한하기
    private String photo;         //photo path 설정

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Member.class)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Profile(Long id,String name, String introduction, String photo, Member member) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.photo = photo;
        this.member = member;
    }
}
