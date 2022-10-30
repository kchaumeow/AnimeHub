package ru.anime.app.Reposits;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.anime.app.Models.UserAnime;

import java.util.*;
@Repository
public interface UserAnimeRepository extends JpaRepository<UserAnime,Long> {
    UserAnime findUserAnimeByUseridAndAnimeid(Long userid,Long animeid);
    List<UserAnime> findAllByUserid(Long userid);

    boolean existsByUseridAndAnimeid(Long userid,Long animeid);
    void deleteByUseridAndAndId(Long userid,Long animeid);
    void deleteById(Long id);
}
