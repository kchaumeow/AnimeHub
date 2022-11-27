package ru.anime.app.Reposits;

import com.sun.mail.imap.protocol.ID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.anime.app.Models.Anime;

import java.util.List;
import java.util.Set;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    Anime findAnimeById(Long id);
    Anime findAnimeByTitle(String title);
    List<Anime> findAllById(Long id);
    List<Anime> findAll();

    List<Anime> findAllByTV(String tv_name);
    List<Anime> findAllByRating(String rating_name);
    List<Anime> findAllByStatus(String status_name);

    @Transactional
    @Modifying
    @Query("update Anime a set a.title=:title," +
            "a.title_english=:title_english," +
            "a.title_japanese=:title_japanese," +
            "a.TV=:tv," +
            "a.episodes=:episodes," +
            "a.status=:status," +
            "a.duration=:duration," +
            "a.rating=:rating," +
            "a.synopsis=:synopsis," +
            "a.season=:season," +
            "a.year=:year," +
            "a.webp=:webp," +
            "a.jpg=:jpg," +
            "a.from=:from," +
            "a.to=:to where a.id=:mal_id")
    void updateAnime(Long mal_id,
                     String title,
                     String title_english,
                     String title_japanese,
                     String tv,
                     int episodes,
                     String status,
                     String duration,
                     String rating,
                     String synopsis,
                     String season,
                     int year,
                     String webp,
                     String jpg,
                     String from,
                     String to);
}