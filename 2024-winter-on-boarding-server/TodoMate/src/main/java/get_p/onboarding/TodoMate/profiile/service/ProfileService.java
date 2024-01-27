package get_p.onboarding.TodoMate.profiile.service;

import get_p.onboarding.TodoMate.profiile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
}
