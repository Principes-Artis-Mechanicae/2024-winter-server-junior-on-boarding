package get_p.onboarding.TodoMate.todo.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import get_p.onboarding.TodoMate.goal.entity.Goal;
import get_p.onboarding.TodoMate.member.entity.Member;
import get_p.onboarding.TodoMate.todo.entity.Todo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TodoDto {
    private Long goalId;
    private String contents;
    private LocalDate date;

    public Todo toEntity(Goal goal) {
        return Todo.builder()
                .contents(contents)
                .date(date)
                .isDone(Boolean.FALSE)
                .goal(goal)
                .build();
    }
}
