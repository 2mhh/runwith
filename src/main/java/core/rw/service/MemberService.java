package core.rw.service;

import core.rw.domain.Member;
import core.rw.dto.MemberStatsDTO;
import core.rw.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public MemberStatsDTO getMemberStats(Long memberId) {
        Member member = memberRepository.findOne(memberId);

        return new MemberStatsDTO(
                member.getName(),
                member.getWins(),
                member.getLosses(),
                member.getFormattedTotalRunningTime(),
                member.getFormattedTotalRunningTime()
        );
    }


}
