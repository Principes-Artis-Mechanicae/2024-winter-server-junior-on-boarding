package get_p.onboarding.TodoMate.todo.entity;

import get_p.onboarding.TodoMate.goal.entity.Goal;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_id")
    private Long id;

    private String contents;
    private LocalDate date;
    private Boolean isDone;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Goal.class)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @Builder
    public Todo(String contents, LocalDate date, Boolean isDone, Goal goal) {
        this.contents = contents;
        this.date = date;
        this.isDone = isDone;
        this.goal = goal;
    }
}