package ru.anime.app.Reposits;

import com.sun.mail.imap.protocol.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anime.app.Models.Anime;

import java.util.List;
import java.util.Set;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    Anime findAnimeById(Long id);
    Anime findAnimeByTitle(String title);
    List<Anime> findAllById(Long id);

}