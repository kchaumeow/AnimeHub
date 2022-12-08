package ru.anime.app.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.Genre;
import ru.anime.app.Reposits.AnimeRepository;
import ru.anime.app.Reposits.GenreRepository;

import java.util.List;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseService {

    private final AnimeRepository animeRepository;
    private final GenreRepository genreRepository;
    private final ConfirmService confirmService;
    public final AnimeGenreService animeGenreService;

    @Scheduled(fixedRate = 24*60*60*1000)
    @Async
    public void updateGenreTable(){
        List<Genre> genreList=confirmService.getGenreListFromGenreDTOList();
        Genre genre;
        for(int i=0;i<genreList.size();i++){
            genre=genreRepository.findGenreById(genreList.get(i).getId());
            if(genre==null) genreRepository.save(genreList.get(i));
            else{
                genreRepository.updateGenre(genre.getId(),genre.getName());
            }
        }
    }
    @Scheduled(fixedRate = 24*60*60*1000)
    @Scheduled(fixedDelay = 5000)
    public void updateAnimeTable() throws InterruptedException {
        int start = 1;
        int finish = 1034;
        for (int page = start; page <= finish; page++) {
            List<Anime> animeList = confirmService.getAnimeListFromAnimeDTOList(page);
            Anime anime;
            for (int i = 0; i < animeList.size(); i++) {
                anime = animeRepository.findAnimeById(animeList.get(i).getId());
                if (anime == null) animeRepository.save(animeList.get(i));
                else {
                    animeRepository.updateAnime(anime.getId(),
                            anime.getTitle(),
                            anime.getTitle_english(),
                            anime.getTitle_japanese(),
                            anime.getTV(),
                            anime.getEpisodes(),
                            anime.getStatus(),
                            anime.getDuration(),
                            anime.getRating(),
                            anime.getSynopsis(),
                            anime.getSeason(),
                            anime.getYear(),
                            anime.getWebp(),
                            anime.getJpg(),
                            anime.getFrom(),
                            anime.getTo());
                }
            }
            Thread.sleep(2000);
        }
    }
//    public void updateAnimeGenreTable(){
//        List<Anime> animeList=animeRepository.findAll();
//        for(int i=0;i<animeList.size();i++){
//            for(int j=0;j<animeList.get(i).getAnimeGenres().size();j++){
//                animeGenreService.update(animeList.get(i),animeList.get(i).getAnimeGenres().get(j).getGenre());
//            }
//        }
//    }
}
