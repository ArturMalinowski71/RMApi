package com.example.RickAndMorty;


import com.example.RickAndMorty.Model.EpisodeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;


@SpringBootApplication
public class RickAndMortyApplication {

	public static void main(String[] args)  {
		SpringApplication.run(RickAndMortyApplication.class, args);

		EpisodeService episodeService = new EpisodeService();
		List <EpisodeDto> test = new LinkedList<>();
		test = episodeService.getAllEpisodes();

		for (EpisodeDto epki : test){
			System.out.println(epki.getEpisode());
		}

	}

}


