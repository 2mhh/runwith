package core.rw.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"member"})
    private List<Participant> participants = new ArrayList<Participant>();

    private int wins = 0;  // 승리 횟수
    private int losses = 0; // 패배 횟수

    private int rating = 1000;

    private Long totalRunningTime = 0L; // 총 러닝 시간 (초 단위)
    private Long bestRunningTime = null; // 최고 기록 (초 단위)

    public void addMemParticipant(Participant participant) {
        participants.add(participant);
        participant.setMember(this);
    }

    // 승리 횟수 증가
    public void incrementWins() {
        this.wins++;
    }

    // 패배 횟수 증가
    public void incrementLosses() {
        this.losses++;
    }

    // 러닝 기록 추가 메서드
    public void addRunningTime(Long runningTimeInSeconds) {
        if (runningTimeInSeconds <= 0) {
            throw new IllegalArgumentException("Running time ERROR");
        }

        // 총 러닝 시간 갱신
        this.totalRunningTime += runningTimeInSeconds;

        // 최고 기록 갱신
        if (this.bestRunningTime == null || runningTimeInSeconds < this.bestRunningTime) {
            this.bestRunningTime = runningTimeInSeconds;
        }
    }

    public String getFormattedBestRunningTime() {
        return formatTime(this.bestRunningTime);
    }

    public String getFormattedTotalRunningTime() {
        return formatTime(this.totalRunningTime);
    }

    // 시간 포맷 유틸리티
    private String formatTime(Long timeInSeconds) {
        if (timeInSeconds == null) return "No record";
        long hours = timeInSeconds / 3600;
        long minutes = (timeInSeconds % 3600) / 60;
        long seconds = timeInSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }



//    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name ="record_id")
//    private Record record;



//    public void addMemParticipant(Participant participant) {
//        participants.add(participant);
//        participant.setMember(this);
//    }


}
