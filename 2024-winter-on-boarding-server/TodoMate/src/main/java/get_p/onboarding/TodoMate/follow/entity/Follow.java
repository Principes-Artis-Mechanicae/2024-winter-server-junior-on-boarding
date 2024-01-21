package get_p.onboarding.TodoMate.follow.entity;

import get_p.onboarding.TodoMate.profiile.entity.Profile;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Profile.class)
    @JoinColumn(name = "profile_id")
    private Profile follower;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Profile.class)
    @JoinColumn(name = "profile_id")
    private Profile following;

    @Builder
    public Follow(Profile follower, Profile following) {
        this.follower = follower;
        this.following = following;
    }
}
