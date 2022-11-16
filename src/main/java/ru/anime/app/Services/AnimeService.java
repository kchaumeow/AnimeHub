package ru.anime.app.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.ajp.AjpAprProtocol;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anime.app.DTO.AnimeDTO;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.UserAnime;
import ru.anime.app.Reposits.AnimeRepository;
import ru.anime.app.Reposits.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;
    private final GenreService genreService;
    private final AnimeApiService animeApiService;
    private final AnimeGenreService animeGenreService;
    List<Anime> animeList = new ArrayList<>();

    public List<Anime> getAnimeList() {
        return animeRepository.findAll();
    }

    public Anime getAnime(Long id) {
        return animeRepository.findById(id).orElse(null);
    }

    public List<Anime> searchAnimeList(String s) {
        animeList = animeRepository.findAll().stream()
                .filter(anime -> anime.getTitle().toLowerCase().contains(s.toLowerCase())).toList();
        return animeList;
    }

    public void createAnime(Anime anime) {
        animeRepository.save(anime);
    }

}
