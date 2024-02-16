package get_p.onboarding.TodoMate.goal.repository;

import get_p.onboarding.TodoMate.goal.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal,Long> {
    Optional<Goal> findGoalById(Long goalId);
}
