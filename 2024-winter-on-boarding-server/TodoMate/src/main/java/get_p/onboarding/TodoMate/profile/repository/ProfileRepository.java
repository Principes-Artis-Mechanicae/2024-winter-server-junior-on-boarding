package get_p.onboarding.TodoMate.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import get_p.onboarding.TodoMate.profile.entity.Profile;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Optional<Profile> findByMemberId(Long memberId);

}
