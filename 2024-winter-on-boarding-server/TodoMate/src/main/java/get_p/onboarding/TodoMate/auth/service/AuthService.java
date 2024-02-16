package get_p.onboarding.TodoMate.auth.service;

import get_p.onboarding.TodoMate.auth.dto.request.JoinRequestDTO;
import get_p.onboarding.TodoMate.exception.NotFoundException;
import get_p.onboarding.TodoMate.member.entity.Member;
import get_p.onboarding.TodoMate.member.entity.Role;
import get_p.onboarding.TodoMate.member.repository.MemberRepository;
import get_p.onboarding.TodoMate.security.details.CustomUserDetails;
import get_p.onboarding.TodoMate.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final MemberRepository MemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    //회원가입 등록
    @Transactional
    public Long register(JoinRequestDTO joinRequestDTO) {
        if (MemberRepository.existsByEmail(joinRequestDTO.getEmail())) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        Member Member = createMemberFromRequest(joinRequestDTO);
        Member savedMember = MemberRepository.save(Member);
        return savedMember.getId();
    }

    public String login(String email, String password) throws AuthenticationException {
        Member Member = MemberRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("계정을 찾을 수 없습니다."));
        UserDetails userDetails = new CustomUserDetails(Member);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password,userDetails.getAuthorities());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String token = jwtUtil.createJwt(authentication.getName(),Member.getRole(),60*60*10L);

        return token;
    }

    //JoinRequestDTO 로부터 Entity 생성
    private Member createMemberFromRequest(JoinRequestDTO joinRequestDTO) {
        return Member.builder()
                .email(joinRequestDTO.getEmail())
                .password(passwordEncoder.encode(joinRequestDTO.getPassword()))
                .role(Role.valueOf(joinRequestDTO.getRole()))
                .build();
    }
}