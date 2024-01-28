package get_p.onboarding.TodoMate.security.details;

import get_p.onboarding.TodoMate.profiile.entity.Profile;
import get_p.onboarding.TodoMate.profiile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ProfileRepository profileRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByEmail(username);
        CustomUserDetails customUserDetails = new CustomUserDetails(profile);

        return customUserDetails;
    }
}
