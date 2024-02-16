package get_p.onboarding.TodoMate.todo.repository;

import get_p.onboarding.TodoMate.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    Optional<Todo> findTodoById(Long todoId);
    Optional<List<Todo>> findByMemberIdAndDate(Long memberId, LocalDate date);
}
