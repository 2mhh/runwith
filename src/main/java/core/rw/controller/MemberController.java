package core.rw.controller;

import core.rw.domain.Member;
import core.rw.dto.MemberStatsDTO;
import core.rw.repository.MemberRepository;
import core.rw.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;


    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMemberInfo(@PathVariable Long memberId) {
        Member member = memberService.findOne(memberId);
        return ResponseEntity.ok(member);
    }



}
