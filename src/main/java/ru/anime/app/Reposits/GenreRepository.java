package ru.anime.app.Reposits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.Genre;

import java.util.List;
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findGenreByName(String genre_name);
    Genre findGenreById(Long id);

    @Transactional
    @Modifying
    @Query("update Genre g set g.name=:name where g.id=:id")
    void updateGenre(Long id,String name);
}
