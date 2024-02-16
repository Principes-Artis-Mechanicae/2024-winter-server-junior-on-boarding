package get_p.onboarding.TodoMate.profile.service;

import get_p.onboarding.TodoMate.exception.NotFoundException;
import get_p.onboarding.TodoMate.profile.dto.ProfileDto;
import get_p.onboarding.TodoMate.profile.dto.ProfileUpdateDto;
import get_p.onboarding.TodoMate.profile.entity.Profile;
import get_p.onboarding.TodoMate.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Transactional
    public Profile createProfile(ProfileDto profileDto) {
        Profile profile = profileDto.toEntity();
        profileRepository.save(profile);
        return profile;
    }

    @Transactional
    public Profile updateProfile(ProfileUpdateDto profileUpdateDto) {
        Profile profile = profileRepository.findByMemberId(profileUpdateDto.getId()).orElseThrow(() -> new NotFoundException("프로필이 존재하지 않습니다."));

        Profile updatedProfile = Profile.builder()
                .id(profile.getId())
                .photo(profileUpdateDto.getPhoto())
                .name(profileUpdateDto.getName())
                .introduction(profileUpdateDto.getIntroduction())
                .build();

        return profileRepository.save(updatedProfile);
    }
}
