package ru.anime.app.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.UserAnime;
import ru.anime.app.Reposits.AnimeRepository;
import ru.anime.app.Reposits.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;
    List<Anime> animeList=new ArrayList<>();

    public List<Anime> getAnimeList(){
        return animeRepository.findAll();
    }
    public Anime getAnime(Long id){
        return animeRepository.findById(id).orElse(null);
    }


//    {
//        BufferedReader reader;
//        String line;
//        StringBuilder responseContent = new StringBuilder();
//        try {
//            URL url = new URL("https://raw.githubusercontent.com/jikan-me/jikan-rest/master/storage/api-docs/api-docs.json");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(5000);
//            connection.setReadTimeout(5000);
//
//            int status = connection.getResponseCode();
//            if (status > 299){
//                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//                while ((line = reader.readLine()) != null){
//                    responseContent.append(line);
//                }
//                reader.close();
//            }else {
//                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
