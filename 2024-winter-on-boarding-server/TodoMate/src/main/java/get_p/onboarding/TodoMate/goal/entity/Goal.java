package get_p.onboarding.TodoMate.goal.entity;

import get_p.onboarding.TodoMate.member.entity.Member;
import get_p.onboarding.TodoMate.todo.entity.Todo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Long id;

    private String title;

    @Enumerated(value = EnumType.STRING)
    private Visibility visibility;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member.class)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>();

    @Builder
    public Goal(Long id,String title, Visibility visibility, Member member, List<Todo> todos) {
        this.id = id;
        this.title = title;
        this.visibility = visibility;
        this.member = member;
        this.todos = todos;
    }
}