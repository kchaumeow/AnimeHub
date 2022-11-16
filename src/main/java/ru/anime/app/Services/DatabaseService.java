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

//    public void saveGenreInDataBaseFromAPI(){
//        List<Genre> genreList=confirmService.getGenreListFromGenreDTOList();
//        for (int i=0;i<genreList.size();i++){
//            genreRepository.save(genreList.get(i));
//        }
//    }
//    public void saveAnimeInDataBaseFromApi(){
//        List<Anime> animeList=confirmService.getAnimeListFromAnimeDTOList();
//        for(int i=0;i<animeList.size();i++){
//            animeRepository.save(animeList.get(i));
//        }
//    }

    @Scheduled(fixedRate = 24*60*60*1000)
    @Async
    public void updateGenreTable(){
        List<Genre> genreList=confirmService.getGenreListFromGenreDTOList();
        Genre genre;
        for(int i=0;i<genreList.size();i++){
            genre=genreRepository.findGenreById(genreList.get(i).getId());
            if(genre==null) genreRepository.save(genreList.get(i));
            else{
                genre=genreList.get(i);
                genreRepository.deleteById(genreList.get(i).getId());
                genreRepository.save(genre);
            }
        }
    }
    @Scheduled(fixedRate = 24*60*60*1000)
    @Scheduled(fixedDelay = 5000)
    public void updateAnimeTable() throws InterruptedException {
        int start=1;
        int finish=1;
        for(int page=start;page<=finish;page++) {
            List<Anime> animeList = confirmService.getAnimeListFromAnimeDTOList(page);
            Anime anime;
            for (int i = 0; i < animeList.size(); i++) {
                anime = animeRepository.findAnimeById(animeList.get(i).getId());
                if (anime == null) animeRepository.save(animeList.get(i));
                else {
                    anime = animeList.get(i);
                    animeRepository.deleteById(animeList.get(i).getId());
                    animeRepository.save(anime);
                }
            }
//            Thread.sleep(2000);
        }
    }
}
