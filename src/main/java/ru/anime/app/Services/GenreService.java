package ru.anime.app.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anime.app.Models.Genre;
import ru.anime.app.Reposits.GenreRepository;

import java.util.List;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    int limit=20;

    public Genre getGenreByName(String genre_name){
        return genreRepository.findGenreByName(genre_name);
    }
    public List<Genre> getGenreList(){
        return genreRepository.findAll();
    }

}
