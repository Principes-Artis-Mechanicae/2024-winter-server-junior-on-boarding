package get_p.onboarding.TodoMate.profile.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import get_p.onboarding.TodoMate.profile.entity.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProfileUpdateDto {
    private Long id;
    private String name;
    private String introduction;
    private String photo;

    public Profile toEntity() {
        return Profile.builder()
                .id(this.id)
                .name(this.name)
                .introduction(this.introduction)
                .photo(this.photo)
                .build();
    }
}
