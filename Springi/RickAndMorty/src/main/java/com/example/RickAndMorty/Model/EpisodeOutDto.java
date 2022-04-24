package com.example.RickAndMorty.Model;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeOutDto {

    String name ;
    String created;
    List <String> charactersName;
    List <String> urls ;

}
