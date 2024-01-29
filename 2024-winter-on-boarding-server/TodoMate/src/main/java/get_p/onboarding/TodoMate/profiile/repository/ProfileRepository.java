package get_p.onboarding.TodoMate.profiile.repository;

import get_p.onboarding.TodoMate.profiile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Boolean existsByEmail(String email);

    Profile findByEmail(String email);
}
