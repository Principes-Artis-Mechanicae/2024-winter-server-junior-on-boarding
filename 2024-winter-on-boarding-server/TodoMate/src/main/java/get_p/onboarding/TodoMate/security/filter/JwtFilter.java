package get_p.onboarding.TodoMate.security.filter;

import get_p.onboarding.TodoMate.member.entity.Member;
import get_p.onboarding.TodoMate.member.repository.MemberRepository;
import get_p.onboarding.TodoMate.security.details.CustomUserDetails;
import get_p.onboarding.TodoMate.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final MemberRepository MemberRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Authorization 헤더 찾음
        String authorization = request.getHeader("Authorization");

        //헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.info("token null");
            filterChain.doFilter(request,response);
            return;
        }
        //Bearer 제거 순수 토큰
        String token = authorization.split(" ")[1];
        //토큰 시간 검증
        if (jwtUtil.isExpired(token)) {
            log.info("token expired");
            filterChain.doFilter(request,response);
            return;
        }
        //값 setting
        String userEmail = jwtUtil.getEmail(token);
        Member Member = MemberRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("계정을 찾을 수 없습니다."));
        //Details에 객체 정보 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(Member);
        //인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails,null, customUserDetails.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request,response);
    }
}
