package ru.anime.app.Reposits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.Genre;

import java.util.List;
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findGenreByName(String genre_name);
    Genre findGenreById(Long id);
}
