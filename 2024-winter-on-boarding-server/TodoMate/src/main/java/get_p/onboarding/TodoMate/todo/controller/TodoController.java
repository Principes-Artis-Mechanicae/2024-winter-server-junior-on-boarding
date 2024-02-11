package get_p.onboarding.TodoMate.todo.controller;

import get_p.onboarding.TodoMate.goal.entity.Goal;
import get_p.onboarding.TodoMate.goal.service.GoalService;
import get_p.onboarding.TodoMate.member.entity.Member;
import get_p.onboarding.TodoMate.member.service.MemberService;
import get_p.onboarding.TodoMate.security.details.CustomUserDetails;
import get_p.onboarding.TodoMate.todo.dto.TodoDto;
import get_p.onboarding.TodoMate.todo.entity.Todo;
import get_p.onboarding.TodoMate.todo.service.TodoService;
import get_p.onboarding.TodoMate.utils.ApiUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TodoController {
    private final TodoService todoService;
    private final MemberService memberService;
    private final GoalService goalService;

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getTodosForMemberAndDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @PathVariable Long memberId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // 현재 사용자의 회원 ID와 요청된 회원 ID가 일치하는지 확인
        if (!userDetails.getMember().getId().equals(memberId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ApiUtil.error(401, "권한 없음"));
        }

        // 요청된 회원 ID로 회원 정보 조회
        Member member = memberService.findMemberById(memberId);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiUtil.error(404, "존재하지 않는 회원"));
        }

        // 할 일 조회
        List<Todo> todos = todoService.getTodosByMemberAndDate(memberId, date);
        return ResponseEntity
                .status(200)
                .body(ApiUtil.success(200));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyTodos(@RequestParam("date") LocalDate date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiUtil.error(401,"권한 없음"));
        }

        // 현재 로그인한 사용자의 ID를 가져옵니다.
        Long memberId = userDetails.getMember().getId();

        // 현재 로그인한 사용자의 ID와 날짜를 기준으로 할 일을 조회합니다.
        List<Todo> myTodos = todoService.getTodosByMemberAndDate(memberId, date);

        return ResponseEntity.ok(ApiUtil.success(200));
    }

    @PostMapping("/me")
    public ResponseEntity<?> createTodo(@RequestBody TodoDto todoDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Goal goal = goalService.getGoalById(todoDto.getGoalId());
        if (!goal.getMember().equals(userDetails.getMember())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiUtil.error(401,"권한 없음"));
        }

        if (goal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiUtil.error(404,"존재하지 않는 목표."));
        }
        todoService.createTodo(todoDto, goal.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiUtil.success(201));
    }

    @PutMapping("/me/{taskId}")
    public ResponseEntity<?> updateTodo(@PathVariable Long todoId, @Valid @RequestBody TodoDto todoDto) {

        // 현재 로그인한 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // 수정할 할 일이 존재하는지 확인
        Todo todo = todoService.getTodoById(todoId);
        if (todo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiUtil.error(404, "존재하지 않는 할 일."));
        }
            // 수정할 할 일이 현재 로그인한 사용자의 것인지 확인
        Goal goal = goalService.getGoalById(todo.getGoal().getId());
        if(goal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiUtil.error(404,"존재하지 않는 목표"));
        }
        if (!goal.getMember().equals(userDetails.getMember())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                   .body(ApiUtil.error(401, "권한 없음"));
        }

        todoService.updateTodo(todoId,todoDto);

        return ResponseEntity.status(HttpStatus.CREATED)
               .body(ApiUtil.success(201));

    }

    @DeleteMapping("/me/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long todoId) {

            // 현재 로그인한 사용자 정보 가져오기
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // 삭제할 할 일이 존재하는지 확인
            Todo todo = todoService.getTodoById(todoId);
            if (todo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiUtil.error(404, "존재하지 않는 할 일."));
            }

            // 삭제할 할 일이 현재 로그인한 사용자의 것인지 확인
            Goal goal = goalService.getGoalById(todo.getGoal().getId());
            if (goal == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiUtil.error(404,"존재하지 않는 목표"));
            }
            if (!goal.getMember().equals(userDetails.getMember())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiUtil.error(401, "권한 없음"));
            }

            // 할 일 삭제
            todoService.deleteTodo(todoId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(ApiUtil.success(204));
    }
}
