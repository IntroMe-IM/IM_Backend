package kr.co.introme.introme.domain.card.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class CardUpdateRequest {
    private String name;
    private String description;
    private String company;
    private String color;
}
