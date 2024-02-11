package get_p.onboarding.TodoMate.todo.service;

import get_p.onboarding.TodoMate.exception.NotFoundException;
import get_p.onboarding.TodoMate.goal.entity.Goal;
import get_p.onboarding.TodoMate.goal.repository.GoalRepository;
import get_p.onboarding.TodoMate.todo.dto.TodoDto;
import get_p.onboarding.TodoMate.todo.entity.Todo;
import get_p.onboarding.TodoMate.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository todoRepository;
    private final GoalRepository goalRepository;

    public Todo getTodoById(Long todoId) {
        Todo todo = todoRepository.findTodoById(todoId).orElseThrow(()->new NotFoundException("할 일을 찾을 수 없습니다."));

        return todo;
    }
    public List<Todo> getTodosByMemberAndDate(Long memberId, LocalDate date) {
        List<Todo> todos = todoRepository.findByMemberIdAndDate(memberId,date).orElseThrow(()->new NotFoundException("할일을 찾을 수 없습니다.")) ;
        return todos;
    }

    @Transactional
    public void createTodo(TodoDto todoDto, Long goalId) {
        Goal goal = goalRepository.findGoalById(goalId).orElseThrow(()->new NotFoundException("목표를 찾을 수 없습니다."));
        Todo todo = todoDto.toEntity(goal);

        todoRepository.save(todo);
    }

    @Transactional
    public void updateTodo(Long todoId, TodoDto todoDto) {
        Todo todo = getTodoById(todoId);
        Todo updatedTodo = Todo.builder()
                .goal(todo.getGoal())
                .contents(todoDto.getContents())
                .date(todoDto.getDate())
                .isDone(todo.getIsDone())
                .build();

        todoRepository.save(updatedTodo);
    }

    @Transactional
    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findTodoById(todoId).orElseThrow(()->new NotFoundException("할 일을 찾을 수 없습니다."));
        todoRepository.delete(todo);
    }
}
