package ru.anime.app.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.AnimeGenre;
import ru.anime.app.Models.Genre;
import ru.anime.app.Reposits.AnimeGenreRepository;
import ru.anime.app.Reposits.AnimeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class AnimeGenreService {
    private final AnimeGenreRepository animeGenreRepository;
    private final AnimeRepository animeRepository;

    public List<AnimeGenre> getAnimeGenreListByGenre(Genre genre){
        return animeGenreRepository.findAllByGenre(genre);
    }
//    public List<AnimeGenre> getAnimeGenreListByAnime(Anime anime){
//        return animeGenreRepository.findAllByAnime(anime);
//    }
//    public List<AnimeGenre> createAnimeGenreListByAnimeIDAndGenreList(Long anime_id,List<Genre> genreList){
//        List<AnimeGenre> animeGenresList=new ArrayList<>();
//        AnimeGenre animeGenre;
//        for(int i=0;i<genreList.size();i++){
//            animeGenre=new AnimeGenre(animeRepository.findAnimeById(anime_id),genreList.get(i));
//            animeGenresList.add(animeGenre);
//        }
//        return animeGenresList;
//    }
}
