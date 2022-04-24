package com.example.RickAndMorty.Model;

import com.example.RickAndMorty.EpisodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EpisodeController {

    private final EpisodeService episodeService;

    @CrossOrigin
    @GetMapping("/episodes")
    public String getEpisodes(){
        List<EpisodeDto> list = episodeService.getAllEpisodes();
        return episodeService.getSeasonInformation(list);
    }


}

