package get_p.onboarding.TodoMate.profile.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import get_p.onboarding.TodoMate.profile.entity.Profile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProfileDto {
    private String name;
    private String introduction;
    private String photo;

    public Profile toEntity() {
        return Profile.builder()
                .name(this.name)
                .introduction(this.introduction)
                .photo(this.photo)
                .build();
    }
}
