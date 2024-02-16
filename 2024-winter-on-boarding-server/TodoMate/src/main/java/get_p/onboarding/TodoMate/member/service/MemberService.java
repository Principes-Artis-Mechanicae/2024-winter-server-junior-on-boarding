package get_p.onboarding.TodoMate.member.service;

import get_p.onboarding.TodoMate.exception.NotFoundException;
import get_p.onboarding.TodoMate.member.entity.Member;
import get_p.onboarding.TodoMate.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Member not found for email: " + email));
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("Member not found for id: " + memberId));
    }

}
