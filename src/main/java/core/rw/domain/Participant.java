package core.rw.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "participant")
@Getter
@NoArgsConstructor()
public class Participant {

    @Id
    @GeneratedValue
    @Column(name = "participant_id")
    private Long id;


    // 연관관계 설정 메서드
    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "match_id")
    private Match match;



//    public static Participant createParticipant(Member member) {
//        Participant participant = new Participant();
//        member.getParticipants().add(participant);
//        participant.member = member;
//        return participant;
//    }
}
