package get_p.onboarding.TodoMate.auth.controller;

import get_p.onboarding.TodoMate.auth.dto.request.JoinRequestDTO;
import get_p.onboarding.TodoMate.auth.service.AuthService;
import get_p.onboarding.TodoMate.utils.ApiUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiUtil.ApiSuccessResult<Long> signup(
            @Valid @RequestBody JoinRequestDTO requestDto
    ) {
        return ApiUtil.success(authService.register(requestDto));
    }

//    @PostMapping("/login")
//    public

}
