package get_p.onboarding.TodoMate.member.controller;

import get_p.onboarding.TodoMate.member.entity.Member;
import get_p.onboarding.TodoMate.member.service.MemberService;
import get_p.onboarding.TodoMate.profile.dto.ProfileUpdateDto;
import get_p.onboarding.TodoMate.utils.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import get_p.onboarding.TodoMate.profile.dto.ProfileDto;
import get_p.onboarding.TodoMate.profile.entity.Profile;
import get_p.onboarding.TodoMate.profile.service.ProfileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ApiUtil.ApiSuccessResult<Member>> searchMemberByEmail(@RequestParam String email) {
        Member member = memberService.findMemberByEmail(email);
        return ResponseEntity
                .ok(ApiUtil.success(member));
    }

    // 회원 상세 검색
    @GetMapping("/{memberId}")
    public ResponseEntity<ApiUtil.ApiSuccessResult<Member>> getMemberDetails(@PathVariable Long memberId) {
        Member member = memberService.findMemberById(memberId);
        return ResponseEntity.ok(ApiUtil.success(member));
    }

    @PostMapping("/me/profile")
    public ResponseEntity<ApiUtil.ApiSuccessResult<Profile>> registerProfile(@RequestBody ProfileDto profileDto) {
        Profile profile = profileService.createProfile(profileDto);

        return ResponseEntity
                .ok(ApiUtil.success(profile));
    }

    @PutMapping("/me/profile")
    public ResponseEntity<ApiUtil.ApiSuccessResult<Profile>> updateProfile(@RequestBody ProfileUpdateDto profileUpdateDto) {
        Profile profile = profileService.updateProfile(profileUpdateDto);
        return ResponseEntity
                .ok(ApiUtil.success(profile));
    }
}
