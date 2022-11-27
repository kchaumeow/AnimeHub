package ru.anime.app.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.User;
import ru.anime.app.Models.UserAnime;
import ru.anime.app.Reposits.UserAnimeRepository;

import java.util.List;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class UserAnimeService {
    private final UserAnimeRepository userAnimeRepository;

    public void createUserAnime(UserAnime userAnime){
        userAnime.getUser().getUserAnimeList().add(userAnime);
        userAnimeRepository.save(userAnime);
        log.info("User with id="+userAnime.getUser().getId()+" added anime with id="+userAnime.getAnime().getId()+" (status="+userAnime.getStatus()+")");
    }

    public UserAnime readUserAnime(User user,Anime anime){
        return userAnimeRepository.findByUserAndAnime(user,anime);
    }
    public void updateUserAnime(UserAnime userAnime){
        UserAnime userAnime1=userAnime.getUser().getUserAnimeList().stream()
                .filter(userAnime2 -> userAnime2.getAnime().equals(userAnime.getAnime()))
                .findAny().get();
        userAnime.getUser().getUserAnimeList().remove(userAnime1);
        userAnimeRepository.delete(userAnime1);
        userAnime.getUser().getUserAnimeList().add(userAnime);
        userAnimeRepository.save(userAnime);
        log.info("User with id="+userAnime.getUser().getId()+" updated anime with id="+userAnime.getAnime().getId()+"(status = "+userAnime.getStatus()+")");
    }

    public boolean isExist(UserAnime userAnime){
        if(userAnimeRepository.existsByUserAndAnime(userAnime.getUser(),userAnime.getAnime())) return true;
        return false;
    }

    public void deleteUserAnime(UserAnime userAnime){
        if(isExist(userAnime)){
            userAnime.getUser().getUserAnimeList().remove(userAnime);
            userAnimeRepository.deleteByUserAndAnime(userAnime.getUser(),userAnime.getAnime());
            log.info("User with id="+userAnime.getUser().getId()+" deleted anime with id="+userAnime.getAnime().getId());
        }
    }

    public List<UserAnime> searchUserAnimeList(String title) {
        return userAnimeRepository.findAll().stream().filter(userAnime -> userAnime.getAnime().getTitle().toLowerCase().contains(title.toLowerCase())).toList();
    }
}
