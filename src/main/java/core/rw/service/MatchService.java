package core.rw.service;

import core.rw.domain.Match;
import core.rw.domain.MatchStatus;
import core.rw.domain.Member;
import core.rw.domain.Participant;
import core.rw.dto.MatchResultDTO;
import core.rw.dto.MemberStatsDTO;
import core.rw.repository.MatchRepository;
import core.rw.repository.MemberRepository;
import core.rw.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final MemberRepository memberRepository;
    private final ParticipantRepository participantRepository;

    @Transactional
    public Long startMatch(Long memberId, Long memberId2) {
        Member one = memberRepository.findOne(memberId);
        Member two = memberRepository.findOne(memberId2);

//        Participant participant1 = Participant.createParticipant(one);
//        Participant participant2 = Participant.createParticipant(two);

        Participant participant1 = new Participant();
        Participant participant2 = new Participant();

        one.addMemParticipant(participant1);
        two.addMemParticipant(participant2);

        Match match = new Match();
        match.setStatus(MatchStatus.READY);  // 초기 상태는 READY
        match.addParticipant(participant1);
        match.addParticipant(participant2);

        participantRepository.save(participant1);
        participantRepository.save(participant2);

        matchRepository.save(match);

        return match.getId();
    }

    // 매치 진행 상태로 변경
    @Transactional
    public void startMatchProgress(Long matchId) throws Exception {
        Match match = matchRepository.findOne(matchId);

        if (match.getStatus() != MatchStatus.READY) {
            throw new IllegalStateException("Match cannot start. Current status: " + match.getStatus());
        }

        match.setStatus(MatchStatus.IN_PROGRESS);
        matchRepository.save(match);
    }

    // 매치 결과 처리 서비스 (승자, 패자)
    @Transactional
    public void processMatchResult(MatchResultDTO matchResultDTO) {
        // 매치 정보 조회
        Match match = matchRepository.findOne(matchResultDTO.getMatchId());
        Member member = memberRepository.findOne(matchResultDTO.getMemberId());
        if(matchResultDTO.isWin()){
            member.incrementWins();
        }
        else{
            member.incrementLosses();
        }
        // 매치 기록 업데이트
        member.addRunningTime(matchResultDTO.getRunningTimeInSeconds());
        memberRepository.save(member);

        // 매치 상태 업데이트
        match.setStatus(MatchStatus.COMP);
        matchRepository.save(match);

    }



//    @Transactional
//    public void addRunningTime(Long memberId, Long runningTimeInSeconds) {
//        Member member = memberRepository.findOne(memberId);
//
//        // 러닝 기록 추가
//        member.addRunningTime(runningTimeInSeconds);
//
//        // 멤버 저장
//        memberRepository.save(member);
//    }
//
//    @Transactional
//    public void incrementWin(Long memberId) {
//        Member member = memberRepository.findOne(memberId);
//
//        // 승리 기록 추가
//        member.incrementWins();
//
//        // 멤버 저장
//        memberRepository.save(member);
//    }
//
//    @Transactional
//    public void incrementLoss(Long memberId) {
//        Member member = memberRepository.findOne(memberId);
//
//        // 패배 기록 추가
//        member.incrementLosses();
//
//        // 멤버 저장
//        memberRepository.save(member);
//    }







}
