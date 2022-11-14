package ru.anime.app.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import ru.anime.app.DTO.AnimeDTO;
import ru.anime.app.DTO.AnimeResponse;
import ru.anime.app.DTO.AnimeSearchResponse;

@Service
public class AnimeApiService {
    private WebClient webClient;

    public AnimeApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<AnimeDTO> getAnimeList(int limit) {
        AnimeSearchResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/anime").queryParam("limit", limit).build()).retrieve()
                .bodyToMono(AnimeSearchResponse.class).block();

        return response.getData();
    }

    public AnimeDTO getAnimeById(int id) {
        AnimeResponse response = webClient.get().uri(uriBuilder -> uriBuilder.path("/anime/{id}/full").build(id))
                .retrieve().bodyToMono(AnimeResponse.class).block();

        return response.getData();
    }
}
