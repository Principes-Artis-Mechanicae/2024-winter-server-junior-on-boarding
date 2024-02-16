package get_p.onboarding.TodoMate.follow.controller;

import get_p.onboarding.TodoMate.exception.NotFoundException;
import get_p.onboarding.TodoMate.follow.entity.Follow;
import get_p.onboarding.TodoMate.follow.service.FollowService;
import get_p.onboarding.TodoMate.utils.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowService followService;

    // 팔로우 목록 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<ApiUtil.ApiSuccessResult<List<Follow>>> getFollowList(@PathVariable Long memberId) {
        List<Follow> followList = followService.getFollowList(memberId);
        return ResponseEntity.ok(ApiUtil.success(followList));
    }

    // 팔로우
    @PostMapping("/me/{memberId}")
    public ResponseEntity<String> followMember(@PathVariable Long memberId) {
        try {
            followService.followMember(memberId);
            return ResponseEntity.ok("팔로우가 성공적으로 등록되었습니다.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }

    @DeleteMapping("/me/{memberId}")
    public ResponseEntity<String> unfollowMember(@PathVariable Long memberId) {
        try {
            followService.unfollowMember(memberId);
            return ResponseEntity.ok("언팔로우가 성공적으로 처리되었습니다.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }
}