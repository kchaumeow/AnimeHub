package ru.anime.app.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.User;
import ru.anime.app.Models.UserAnime;
import ru.anime.app.Reposits.AnimeRepository;
import ru.anime.app.Reposits.UserAnimeRepository;
import ru.anime.app.Reposits.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class UserAnimeService {
    private final UserAnimeRepository userAnimeRepository;
    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;

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


//    public void createUserAnime(UserAnime userAnime){
//        userAnimeRepository.save(userAnime);
//        log.info("User with id="+userAnime.getUserid()+" added anime with id="+userAnime.getAnimeid()+" (status="+userAnime.getStatus()+")");
//    }
//
//    public void updateUserAnime(UserAnime userAnime){
//        UserAnime userAnime1=userAnimeRepository.findUserAnimeByUseridAndAnimeid(userAnime.getUserid(),
//                userAnime.getAnimeid());
//        userAnimeRepository.deleteById(userAnime1.getId());
//        userAnimeRepository.save(userAnime);
//        log.info("User with id="+userAnime.getUserid()+" updated anime with id="+userAnime.getAnimeid()+" (new status="+userAnime.getStatus()+")");
//    }
//
//    public UserAnime readUserAnime(Long user_id,Long anime_id){
//        return userAnimeRepository.findUserAnimeByUseridAndAnimeid(user_id,anime_id);
//    }
//
//    public void deleteUserAnime(UserAnime userAnime){
//        userAnimeRepository.deleteById(userAnimeRepository.findUserAnimeByUseridAndAnimeid(userAnime.getUserid(),
//                userAnime.getAnimeid()).getId());
//        log.info("User with id="+userAnime.getUserid()+" deleted anime with id="+userAnime.getAnimeid());
//    }
//
//    public boolean isExist(UserAnime userAnime){
//        return userAnimeRepository.existsByUseridAndAnimeid(userAnime.getUserid(),
//                userAnime.getAnimeid());
//    }
//
//    public List<UserAnime> getUserAnimeList(Long user_id){
////        if(userAnimeRepository.findAllByUserid(user_id).isEmpty()){
////            log.info("HUUUUUUUUI");
////        }
////        return userAnimeRepository.findAllByUserid(user_id);
//        return userAnimeRepository.findAll().stream().filter(userAnime -> userAnime.getUserid()==user_id).toList();
//    }
//    public Anime getAnimeFromUserAnime(UserAnime userAnime){
//        return animeRepository.findAnimeById(userAnime.getAnimeid());
//    }
//    public List<Anime> selectUserAnimeList(Long user_id){
//        List<Anime> animeList=new ArrayList<>();
//        List<UserAnime> userAnimeList=userAnimeRepository.findAllByUserid(user_id);
//        for(UserAnime userAnime:userAnimeList){
//            animeList.add(getAnimeFromUserAnime(userAnime));
//        }
//        return animeList;
//    }

}
