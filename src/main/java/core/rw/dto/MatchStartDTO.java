package core.rw.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MatchStartDTO {
    private Long memberId1;

    private Long memberId2;

}
