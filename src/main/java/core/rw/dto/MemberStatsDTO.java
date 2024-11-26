package core.rw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
public class MemberStatsDTO {

    private String name;
    private int wins;
    private int losses;
    private String totalRunningTime;
    private String bestRunningTime;

    public MemberStatsDTO(String name, int wins, int losses, String totalRunningTime, String bestRunningTime) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.totalRunningTime = totalRunningTime;
        this.bestRunningTime = bestRunningTime;
    }

    // Getter
    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public String getTotalRunningTime() {
        return totalRunningTime;
    }

    public String getBestRunningTime() {
        return bestRunningTime;
    }
}