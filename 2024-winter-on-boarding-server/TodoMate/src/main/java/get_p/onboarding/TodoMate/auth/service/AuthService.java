package get_p.onboarding.TodoMate.auth.service;

import get_p.onboarding.TodoMate.auth.dto.request.JoinRequestDTO;
import get_p.onboarding.TodoMate.auth.dto.request.LoginRequestDTO;
import get_p.onboarding.TodoMate.auth.dto.response.LoginResponseDTO;
import get_p.onboarding.TodoMate.profiile.entity.Profile;
import get_p.onboarding.TodoMate.profiile.repository.ProfileRepository;
import get_p.onboarding.TodoMate.security.filter.LoginFilter;
import get_p.onboarding.TodoMate.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final LoginFilter loginFilter;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    //회원가입 등록
    @Transactional
    public Long register(JoinRequestDTO joinRequestDTO) {
        if (profileRepository.existsByEmail(joinRequestDTO.getEmail())) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        Profile profile = createProfileFromRequest(joinRequestDTO);
        Profile savedProfile = profileRepository.save(profile);
        return savedProfile.getId();
    }

    public String login(String email, String password) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String token = jwtUtil.createJwt(authentication.getName(),60*60*10L);

        return token;
    }
    //JoinRequestDTO 로부터 Entity 생성
    private Profile createProfileFromRequest(JoinRequestDTO joinRequestDTO) {
        return Profile.builder()
                .name(joinRequestDTO.getName())
                .introduction(joinRequestDTO.getIntroduction())
                .email(joinRequestDTO.getEmail())
                .password(passwordEncoder.encode(joinRequestDTO.getPassword()))
                .build();
    }
}
