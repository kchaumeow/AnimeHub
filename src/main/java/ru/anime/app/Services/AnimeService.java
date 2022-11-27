package ru.anime.app.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anime.app.Models.Anime;
import ru.anime.app.Reposits.AnimeRepository;


import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;
    List<Anime> animeList = new ArrayList<>();

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

    public Page<Anime> getPage(int p){
        Pageable pageable= PageRequest.of(p,25);
        return animeRepository.findAll(pageable);
    }


}
