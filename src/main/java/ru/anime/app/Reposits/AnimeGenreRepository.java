package ru.anime.app.Reposits;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.AnimeGenre;
import ru.anime.app.Models.Genre;

import java.util.List;

public interface AnimeGenreRepository extends JpaRepository<AnimeGenre, Long> {
    List<AnimeGenre> findAllByAnime(Anime anime);
    //List<AnimeGenre> findAllByGenre(Genre genre);

    List<AnimeGenre> findAllByAnimeAndGenre(Anime anime,Genre genre);

    List<AnimeGenre> findAllByGenre(Genre genre);

    Page<AnimeGenre> findAllByGenre(Genre genre, Pageable pageable);

    AnimeGenre findAnimeGenreByAnimeAndGenre(Anime anime,Genre genre);
    @Transactional
    @Modifying
    @Query("update AnimeGenre ag set ag.anime=:anime, ag.genre=:genre where ag.id=:id")
    void updateAnimeGenre(Long id, Anime anime,Genre genre);
}
