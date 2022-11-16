package ru.anime.app.Reposits;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.AnimeGenre;
import ru.anime.app.Models.Genre;

import java.util.List;

public interface AnimeGenreRepository extends JpaRepository<AnimeGenre, Long> {
    List<AnimeGenre> findAllByAnime(Anime anime);
    List<AnimeGenre> findAllByGenre(Genre genre);
}
