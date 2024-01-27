package get_p.onboarding.TodoMate.security.details;

import get_p.onboarding.TodoMate.profiile.entity.Profile;
import get_p.onboarding.TodoMate.profiile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ProfileRepository profileRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByEmail(email);

        if (profile != null) {
            return new CustomUserDetails(profile);
        }
        return null;
    }
}
