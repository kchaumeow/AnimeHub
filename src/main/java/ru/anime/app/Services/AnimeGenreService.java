package ru.anime.app.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.AnimeGenre;
import ru.anime.app.Models.Genre;
import ru.anime.app.Reposits.AnimeGenreRepository;
import ru.anime.app.Reposits.GenreRepository;

import java.util.List;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class AnimeGenreService {
    private final AnimeGenreRepository animeGenreRepository;
    private final GenreRepository genreRepository;

    public List<AnimeGenre> getAnimeGenreListByGenre(Genre genre){

        return animeGenreRepository.findAllByGenre(genre);
    }

    public List<AnimeGenre> getAnimeGenreListByAnimeAndGenre(String animeTitle,String genreName){
        List<AnimeGenre> animeGenreList;
        animeGenreList=animeGenreRepository.findAll().stream().filter(animeGenre ->
                animeGenre.getGenre().getName().equalsIgnoreCase(genreName) &&
                animeGenre.getAnime().getTitle().toLowerCase().contains(animeTitle.toLowerCase())).toList();
        return animeGenreList;
    }

    public Page<AnimeGenre> getAnimeGenreListByGenreNamePage(String genre_name, int p){
        Pageable pageable= PageRequest.of(p,24);
        Genre genre=genreRepository.findGenreByName(genre_name);
        return animeGenreRepository.findAllByGenre(genre, pageable);
    }

//    public void update(Anime anime, Genre genre){
//        AnimeGenre animeGenre= new AnimeGenre(anime,genre);
//        AnimeGenre animeGenre1=animeGenreRepository.findAnimeGenreByAnimeAndGenre(anime,genre);
//        if(animeGenre1==null) animeGenreRepository.save(animeGenre);
//        else{
//            animeGenreRepository.delete(animeGenre1);
//            animeGenreRepository.save(animeGenre);
//        }
//    }


}
