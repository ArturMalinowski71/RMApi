package com.example.RickAndMorty.Model;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SeasonDto {
    List<EpisodeDto> lista ;
}
