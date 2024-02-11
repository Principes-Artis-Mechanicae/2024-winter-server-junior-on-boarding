package get_p.onboarding.TodoMate.config;

import get_p.onboarding.TodoMate.member.repository.MemberRepository;
import get_p.onboarding.TodoMate.security.filter.JwtFilter;
import get_p.onboarding.TodoMate.security.filter.LoginFilter;
import get_p.onboarding.TodoMate.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    private final MemberRepository MemberRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public LoginFilter loginFilter() throws Exception {
        return new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil,MemberRepository);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disable
        http
                .csrf(AbstractHttpConfigurer::disable);

        //form login disable
        http
                .formLogin(AbstractHttpConfigurer::disable);

        //http basic disable
        http
                .httpBasic(AbstractHttpConfigurer::disable);

        //경로별 인가작업
        http
                .authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/api/v1/auth/**","/login").permitAll()
                    .anyRequest().authenticated());

        //JwtFilter
        http
                .addFilterBefore(new JwtFilter(jwtUtil,MemberRepository), LoginFilter.class);

        //로그인 필터
        http
                .addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);

        //세션 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
