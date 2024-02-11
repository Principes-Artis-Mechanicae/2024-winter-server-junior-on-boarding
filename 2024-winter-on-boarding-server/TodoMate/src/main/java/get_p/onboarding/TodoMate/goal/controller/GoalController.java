package get_p.onboarding.TodoMate.goal.controller;

import get_p.onboarding.TodoMate.exception.NotFoundException;
import get_p.onboarding.TodoMate.goal.dto.GoalDto;
import get_p.onboarding.TodoMate.goal.entity.Goal;
import get_p.onboarding.TodoMate.goal.service.GoalService;
import get_p.onboarding.TodoMate.member.entity.Member;
import get_p.onboarding.TodoMate.security.details.CustomUserDetails;
import get_p.onboarding.TodoMate.utils.ApiUtil;
import get_p.onboarding.TodoMate.utils.ApiUtil.ApiErrorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/goals")
public class GoalController {
    private final GoalService goalService;

    @GetMapping("/{memberId}")
    public ResponseEntity<ApiUtil.ApiSuccessResult<List<Goal>>> getGoal(@PathVariable Long memberId) {
        List<Goal> goals = goalService.getGoalsByMemberId(memberId);
        return ResponseEntity.ok(ApiUtil.success(goals));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiUtil.ApiSuccessResult<List<Goal>>> getMyGoal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Member me = userDetails.getMember();
        List<Goal> myGoals = me.getGoals();
        return ResponseEntity.ok(ApiUtil.success(myGoals));
    }
    @PostMapping("/me")
    public ResponseEntity<ApiUtil.ApiSuccessResult<Goal>> createGoal(@RequestBody GoalDto goalDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Member me = userDetails.getMember();
        Goal goal = goalService.createGoal(goalDto, me.getId());

        return ResponseEntity.ok(ApiUtil.success(goal));
    }

    //목표 수정
    @PutMapping("/me/{goalId}")
    public ResponseEntity<?> updateGoal(@PathVariable Long goalId, @RequestBody GoalDto goalDto) {
        Goal goal = goalService.updateGoal(goalId,goalDto);

        return ResponseEntity.ok(ApiUtil.success(goal));
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<?> deleteGoal(@PathVariable Long goalId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiUtil.error(401, "권한 없음"));
        }

        try {
            goalService.deleteGoal(goalId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(ApiUtil.success(204));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiUtil.error(404, "등록되어 있지 않은 목표"));
        }
    }
}

