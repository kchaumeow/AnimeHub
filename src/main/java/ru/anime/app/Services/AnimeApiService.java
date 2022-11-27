package ru.anime.app.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.anime.app.DTO.*;

import java.util.List;

@Service
public class AnimeApiService {
    private WebClient webClient;

    public AnimeApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<AnimeDTO> getAnimeList(int page,int limit){
        AnimeSearchResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/anime").queryParam("page",page).queryParam("limit",limit).build()).retrieve()
                .bodyToMono(AnimeSearchResponse.class).block();

        return response.getData();
    }

    public List<AnimeGenreDTO> getGenreList(int limit){
        AnimeGenresResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/genres/anime").queryParam("limit",limit).build()).retrieve()
                .bodyToMono(AnimeGenresResponse.class).block();

        return response.getData();
    }

}
