package com.example.RickAndMorty;

import com.example.RickAndMorty.Model.EpisodeDto;
import com.example.RickAndMorty.Model.Tech;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class EpisodeService {

    public static final String RICK_AND_MORTY_URL = "https://rickandmortyapi.com/api/episode?page={id}";
    private final RestTemplate restTemplate = new RestTemplate();

    List<EpisodeDto> getAllEpisodes() {
        String id = String.valueOf(1);
        ResponseEntity<Tech> responseEntity =
                restTemplate.getForEntity(RICK_AND_MORTY_URL, Tech.class, id);

        Tech tech = responseEntity.getBody();
        List<EpisodeDto> episodes = tech.getResults();
        int pagesCounter = tech.getInfo().getPages();
        for (int i = 2; i <= pagesCounter; i++) {
            id = String.valueOf(i);
            responseEntity = restTemplate.getForEntity(RICK_AND_MORTY_URL, Tech.class, id);
            episodes.addAll(responseEntity.getBody().getResults());
        }
        return episodes;
    }
    Map getSeasonInformation(List<EpisodeDto> episodes){
        Map<String, Integer> map = new TreeMap<>();
        int counter =0;
        for (EpisodeDto episod : episodes){
            String season = String.valueOf(episod.getEpisode().subSequence(0, 3));
            List<String> keys = (List) map.keySet();
            map.put(season,counter);


        }
        return map;
    }


}
