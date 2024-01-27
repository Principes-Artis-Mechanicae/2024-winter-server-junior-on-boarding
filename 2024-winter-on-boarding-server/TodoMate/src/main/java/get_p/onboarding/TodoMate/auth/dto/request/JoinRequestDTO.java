package get_p.onboarding.TodoMate.auth.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import get_p.onboarding.TodoMate.profiile.entity.Profile;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JoinRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 50, message = "자기소개는 최대 50글자까지 입력 가능합니다.")
    private String introduction;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
