package core.rw.dto;

import core.rw.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private String email;

    private String name;

    public static MemberDto toDto(Member member) {
        return MemberDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .build();
    }


}