package get_p.onboarding.TodoMate.goal.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import get_p.onboarding.TodoMate.goal.entity.Goal;
import get_p.onboarding.TodoMate.goal.entity.Visibility;
import get_p.onboarding.TodoMate.member.entity.Member;
import get_p.onboarding.TodoMate.todo.entity.Todo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoalDto {

    private String title;
    private Visibility visibility;
    private List<Todo> todos;

    public Goal toEntity(Member member) {
        return Goal.builder()
                .member(member)
                .title(this.title)
                .visibility(this.visibility)
                .todos(this.todos)
                .build();
    }
}