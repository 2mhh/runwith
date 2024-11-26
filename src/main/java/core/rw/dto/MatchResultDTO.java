package core.rw.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MatchResultDTO {

    private Long matchId;

    private Long memberId;

    private boolean win; // true-> 승리

    private Long runningTimeInSeconds; // 총 소요시간



}
