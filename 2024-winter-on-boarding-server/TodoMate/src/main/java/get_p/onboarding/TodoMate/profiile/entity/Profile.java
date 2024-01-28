package get_p.onboarding.TodoMate.profiile.entity;

import get_p.onboarding.TodoMate.goal.entity.Goal;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Column(unique = true)
    @Email
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> goals = new ArrayList<>();

    @Builder
    public Profile(String name, String introduction, String photo, String email, String password, Role role, List<Goal> goals) {
        this.name = name;
        this.introduction = introduction;
        this.photo = photo;
        this.email = email;
        this.password = password;
        this.role = role;
        this.goals = goals;
    }
}
