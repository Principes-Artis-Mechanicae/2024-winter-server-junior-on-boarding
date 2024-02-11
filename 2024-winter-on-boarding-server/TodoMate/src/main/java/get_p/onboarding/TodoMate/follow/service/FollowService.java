package get_p.onboarding.TodoMate.follow.service;

import get_p.onboarding.TodoMate.exception.DuplicateFollowException;
import get_p.onboarding.TodoMate.exception.NotFoundException;
import get_p.onboarding.TodoMate.follow.entity.Follow;
import get_p.onboarding.TodoMate.follow.repository.FollowRepository;
import get_p.onboarding.TodoMate.member.entity.Member;
import get_p.onboarding.TodoMate.member.repository.MemberRepository;
import get_p.onboarding.TodoMate.security.details.CustomUserDetails;
import get_p.onboarding.TodoMate.security.details.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
    private final CustomUserDetailsService customUserDetailsService;

    public List<Follow> getFollowList(Long memberId) {
        List<Follow> followList = followRepository.findByMemberId(memberId).orElseThrow(()->new NotFoundException("팔로우 목록을 찾을 수 없습니다."));
        return followList;
    }

    @Transactional
    public void followMember(Long memberId) {
        Member follower = getCurrentLoggedInMember();

        Member followingMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당하는 회원을 찾을 수 없습니다."));

        if (follower.getId().equals(followingMember.getId())) {
            throw new IllegalArgumentException("자기 자신을 팔로우할 수 없습니다.");
        }

        Follow existingFollow = followRepository.findByFollowerAndFollowing(follower, followingMember);
        if (existingFollow != null) {
            throw new DuplicateFollowException("이미 해당 회원을 팔로우하고 있습니다.");
        }

        Follow follow = Follow.builder()
                .follower(follower)
                .following(followingMember)
                .build();

        followRepository.save(follow);
    }

    @Transactional
    public void unfollowMember(Long memberId) {
        Member follower = getCurrentLoggedInMember();

        Member followingMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당하는 회원을 찾을 수 없습니다."));

        if (follower.getId().equals(followingMember.getId())) {
            throw new IllegalArgumentException("자기 자신의 팔로우를 취소할 수 없습니다.");
        }

        Follow follow = followRepository.findByFollowerAndFollowing(follower, followingMember);
        if (follow == null) {
            throw new NotFoundException("해당하는 팔로우를 찾을 수 없습니다.");
        }

        followRepository.delete(follow);
    }

    private Member getCurrentLoggedInMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("인증되지 않은 사용자입니다.");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

//        return memberRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("현재 로그인한 사용자 정보를 찾을 수 없습니다."));
        return (Member) customUserDetailsService.loadUserByUsername(username);
    }
}
