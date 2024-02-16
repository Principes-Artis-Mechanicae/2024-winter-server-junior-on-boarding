package get_p.onboarding.TodoMate.security.details;

import get_p.onboarding.TodoMate.member.entity.Member;
import get_p.onboarding.TodoMate.member.repository.MemberRepository;
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

    private final MemberRepository MemberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        Member Member = MemberRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));
        CustomUserDetails customUserDetails = new CustomUserDetails(Member);

        return customUserDetails;
    }
}
