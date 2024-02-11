package get_p.onboarding.TodoMate.follow.repository;

import get_p.onboarding.TodoMate.follow.entity.Follow;
import get_p.onboarding.TodoMate.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<List<Follow>> findByMemberId(Long memberId);

    Follow findByFollowerAndFollowing(Member follower, Member followingMember);
}
