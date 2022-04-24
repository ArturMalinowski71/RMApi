package com.example.RickAndMorty;

import com.example.RickAndMorty.Model.CharacterDTO;
import com.example.RickAndMorty.Model.EpisodeDto;
import com.example.RickAndMorty.Model.EpisodeOutDto;
import com.example.RickAndMorty.Model.Tech;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
public class EpisodeService {

    public static final String RICK_AND_MORTY_URL = "https://rickandmortyapi.com/api/episode?page={id}";
    private final RestTemplate restTemplate = new RestTemplate();
    Gson gson = new Gson();

    public List<EpisodeDto> getAllEpisodes() {
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
    public String getSeasonInformation(List<EpisodeDto> episodes){

        String result = "";
        List<Integer> numbersOfEpisodes = new LinkedList<>();
        List<String> tempList = new LinkedList<>();
        int counter =1;
        for (EpisodeDto episode : episodes){
            String season = String.valueOf(episode.getEpisode().subSequence(0, 3));
            tempList.add(season);
        }
        for (int i=0;i<tempList.size()-1;i++){
            if(tempList.get(i).equals(tempList.get(i+1))){
                counter++;
            }
            else{
            numbersOfEpisodes.add(counter);
            counter=1;
            }
        }
        numbersOfEpisodes.add(counter);
        StringBuilder stringBuilder = new StringBuilder(result);
        stringBuilder.append(numbersOfEpisodes.size()+" ");
        for (Integer episode : numbersOfEpisodes){
            stringBuilder.append(episode+" ");
        }
        result = gson.toJson(stringBuilder.toString()) ;

        return result;
    }
    public List<EpisodeOutDto> getSeasonEpisodesInformation(List<EpisodeDto> episodes ,String id){
        List<EpisodeOutDto> result = new LinkedList<>();

        for (EpisodeDto episode : episodes){
            String season = String.valueOf(episode.getEpisode().subSequence(0, 3));
            if (season.equals(id)){
                EpisodeOutDto temp = EpisodeOutDto.builder()
                        .name(episode.getName())
                        .created(episode.getCreated())
                        .charactersName(getCharactersNames(episode))
                        .urls(getCharactersUrl(episode))
                        .build();
                result.add(temp);
            }
        }

        return result;
    }
    List<String> getCharactersNames(EpisodeDto episode){
        List<String> results = new LinkedList<>();
        List<String> temp = episode.getCharacters();
        for (String s : temp){
            CharacterDTO response = restTemplate.getForObject(s,CharacterDTO.class);
            CharacterDTO characterDTO = CharacterDTO.builder()
                    .name(response.getName())
                    .url(response.getUrl())
                    .build();
            String temporary = characterDTO.getName();
            results.add(temporary);
        }
        return results;
    }
    List<String> getCharactersUrl(EpisodeDto episode){
        List<String> results = new LinkedList<>();
        List<String> temp = episode.getCharacters();
        for (String s : temp){
            CharacterDTO response = restTemplate.getForObject(s,CharacterDTO.class);
            CharacterDTO characterDTO = CharacterDTO.builder()
                    .name(response.getName())
                    .url(response.getUrl())
                    .build();
            String temporary = characterDTO.getUrl();
            results.add(temporary);
        }
        return results;
    }


}
