package ru.anime.app.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anime.app.DTO.AnimeDTO;
import ru.anime.app.DTO.AnimeGenreDTO;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.AnimeGenre;
import ru.anime.app.Models.Genre;
import ru.anime.app.Reposits.AnimeRepository;
import ru.anime.app.Reposits.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class ConfirmService {

    private final AnimeApiService animeApiService;
    public final AnimeGenreService animeGenreService;
    public final GenreRepository genreRepository;

    int limit_genre=1;
    int limit_anime=25;

    public Anime getAnimeFromAnimeDTO(AnimeDTO animeDTO){
        Anime anime=new Anime(
                animeDTO.getMal_id(),
                animeDTO.getTitle(),
                animeDTO.getTitle_english(),
                animeDTO.getTitle_japanese(),
                animeDTO.getTV(),
                animeDTO.getEpisodes(),
                animeDTO.getStatus(),
                animeDTO.getDuration(),
                animeDTO.getRating(),
                animeDTO.getSynopsis(),
                animeDTO.getSeason(),
                animeDTO.getYear(),
                animeDTO.getImages().getWebp().getImage_url(),
                animeDTO.getImages().getJpg().getImage_url(),
                animeDTO.getAired().getFrom(),
                animeDTO.getAired().getTo());
        List<AnimeGenre> animeGenreList=new ArrayList<>();
        for(int i=0;i<animeDTO.getGenres().size();i++){
            animeGenreList.add(new AnimeGenre(anime,genreRepository.findGenreByName(animeDTO.getGenres().get(i).getName())));
        }
        anime.setAnimeGenres(animeGenreList);
        return anime;
    }

    public List<Anime> getAnimeListFromAnimeDTOList(int page){
        List<AnimeDTO> animeDTOList=animeApiService.getAnimeList(page,limit_anime);
        List<Anime> animeList=new ArrayList<>();
        for(int i=0;i<animeDTOList.size();i++){
            animeList.add(getAnimeFromAnimeDTO(animeDTOList.get(i)));
        }
        return animeList;
    }

    public Genre getGenreFromGenreDTO(AnimeGenreDTO animeGenreDTO){
        Genre genre=new Genre(animeGenreDTO.getMal_id(),animeGenreDTO.getName());
        return genre;
    }

    public List<Genre> getGenreListFromGenreDTOList(){
        List<AnimeGenreDTO> animeGenreDTOList=animeApiService.getGenreList(limit_genre);
        List<Genre> genreList=new ArrayList<>();
        for(int i=0;i<animeGenreDTOList.size();i++){
            genreList.add(getGenreFromGenreDTO(animeGenreDTOList.get(i)));
        }
        return genreList;
    }

//    public List<Genre> getGenreListFromGenreDTOList(List<AnimeGenreDTO> animeGenreDTOList){
//        List<Genre> genreList=new ArrayList<>();
//        for(int i=0;i<animeGenreDTOList.size();i++){
//            genreList.add(getGenreFromGenreDTO(animeGenreDTOList.get(i)));
//        }
//        return genreList;
//    }
}
