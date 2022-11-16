package ru.anime.app.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.anime.app.DTO.*;
import ru.anime.app.Models.Anime;
import ru.anime.app.Reposits.AnimeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeApiService {
    private WebClient webClient;

    public AnimeApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<AnimeDTO> getAnimeList(int page,int limit) {
        AnimeSearchResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/anime").queryParam("page",page).queryParam("limit",limit).build()).retrieve()
                .bodyToMono(AnimeSearchResponse.class).block();

        return response.getData();
    }

//    public AnimeDTO getAnimeById(Long id) {
//        AnimeResponse response = webClient.get().uri(uriBuilder -> uriBuilder.path("/anime/{id}/full").build(id))
//                .retrieve().bodyToMono(AnimeResponse.class).block();
//
//        return response.getData();
//    }

    public List<AnimeGenreDTO> getGenreList(int limit){
        AnimeGenresResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/genres/anime").queryParam("limit",limit).build()).retrieve()
                .bodyToMono(AnimeGenresResponse.class).block();

        return response.getData();
    }

}
