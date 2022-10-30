package ru.anime.app.Reposits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.User;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
