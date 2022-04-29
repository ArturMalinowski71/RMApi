package com.example.RickAndMorty;

import com.example.RickAndMorty.EpisodeService;
import com.example.RickAndMorty.Model.EpisodeDto;
import com.example.RickAndMorty.Model.EpisodeOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @CrossOrigin
    @GetMapping("/episodes/{id}")
    public List<EpisodeOutDto> getEpisodesOnSeason(@PathVariable String id){
        List<EpisodeDto> list = episodeService.getAllEpisodes();
        return episodeService.getSeasonEpisodesInformation(list ,id);
    }


}

