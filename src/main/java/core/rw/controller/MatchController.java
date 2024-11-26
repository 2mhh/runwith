package core.rw.controller;


import core.rw.dto.MatchResultDTO;
import core.rw.dto.MatchStartDTO;
import core.rw.dto.MemberStatsDTO;
import core.rw.service.MatchService;
import core.rw.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final MemberService memberService;

    // 두 멤버 ID로 매치 시작
    @PostMapping("/start")
    public ResponseEntity<Long> startMatch(@RequestBody MatchStartDTO matchStartDTO) {
        Long matchId = matchService.startMatch(matchStartDTO.getMemberId1(), matchStartDTO.getMemberId2());
        return ResponseEntity.ok(matchId);  // 매치 ID를 반환
    }

    // 매치 결과 처리
    @PostMapping("/result")
    public ResponseEntity<Void> receiveMatchResult(@RequestBody MatchResultDTO matchResultDTO) {
        matchService.processMatchResult(matchResultDTO);
        return ResponseEntity.ok().build();  // 성공적으로 처리되었으면 200 상태 코드 반환
    }



    @GetMapping("/{memberId}/stats")
    public ResponseEntity<MemberStatsDTO> getMemberStats(@PathVariable Long memberId) {
        MemberStatsDTO stats = memberService.getMemberStats(memberId);
        return ResponseEntity.ok(stats);
    }

//    // 매치 결과 처리
//    @PostMapping("/result")
//    public ResponseEntity<Void> receiveMatchResult(@RequestBody MatchResultDTO matchResultDTO) {
//        try {
//            matchService.processMatchResult(matchResultDTO);
//            return ResponseEntity.ok().build();  // 200 상태 코드 반환
//        } catch (Exception e) {
//            // 오류 처리
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 상태 코드 반환
//        }
//    }


}
