package core.rw.repository;

import core.rw.domain.Match;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MatchRepository {

    private final EntityManager em;

    public void save(Match match) {
        em.persist(match);
    }

    public Match findOne(Long id) {
        return em.find(Match.class, id);
    }
}
