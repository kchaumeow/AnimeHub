package ru.anime.app.Reposits;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anime.app.Models.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
