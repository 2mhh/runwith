package core.rw.service;

import core.rw.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MatchServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MatchService matchService;
    @Autowired
    MemberRepository memberRepository;

    @Rollback(value = false)
    @Test
    void startMatch() {
        matchService.startMatch(1L,2L);

    }
}