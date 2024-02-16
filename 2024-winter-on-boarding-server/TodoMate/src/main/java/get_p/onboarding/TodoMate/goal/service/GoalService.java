package get_p.onboarding.TodoMate.goal.service;

import get_p.onboarding.TodoMate.exception.NotFoundException;
import get_p.onboarding.TodoMate.goal.dto.GoalDto;
import get_p.onboarding.TodoMate.goal.entity.Goal;
import get_p.onboarding.TodoMate.goal.repository.GoalRepository;
import get_p.onboarding.TodoMate.member.entity.Member;
import get_p.onboarding.TodoMate.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoalService {
    private final GoalRepository goalRepository;
    private final MemberRepository memberRepository;

    public Goal getGoalById(Long goalId) {
        return goalRepository.findGoalById(goalId).orElseThrow(()->new NotFoundException("목표를 찾을 수 없습니다."));
    }
    public List<Goal> getGoalsByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()->new NotFoundException("멤버가 존재하지 않습니다."));

        return member.getGoals();
    }

    @Transactional
    public Goal createGoal(GoalDto goalDto, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()->new NotFoundException("멤버가 존재하지 않습니다."));
        Goal goal = goalDto.toEntity(member);
        goalRepository.save(goal);

        return goal;
    }

    @Transactional
    public Goal updateGoal(Long goalId, GoalDto goalDto) {
        Goal goal = goalRepository.findGoalById(goalId).orElseThrow(()->new NotFoundException("해당 목표가 존재하지 않습니다."));
        Goal updatedGoal = Goal.builder()
                .id(goal.getId())
                .title(goalDto.getTitle() != null ? goalDto.getTitle() : goal.getTitle())
                .visibility(goalDto.getVisibility())
                .todos(goalDto.getTodos() != null ? goalDto.getTodos() : goal.getTodos())
                .build();

        return goalRepository.save(updatedGoal);
    }

    @Transactional
    public void deleteGoal(Long goalId) {
        Goal goal = goalRepository.findGoalById(goalId)
                .orElseThrow(() -> new NotFoundException("등록되어 있지 않은 목표입니다."));

        goalRepository.delete(goal);
    }
}
