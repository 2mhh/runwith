package core.rw.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter @Setter
@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue
    @Column(name = "match_id")
    private Long id;

    private LocalDateTime matchDate;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<Participant> participants = new ArrayList<Participant>();

    @Enumerated(EnumType.STRING)
    private MatchStatus status; // READY, COM

    public void addParticipant(Participant participant) {
        participants.add(participant);
        participant.setMatch(this);
    }







}
