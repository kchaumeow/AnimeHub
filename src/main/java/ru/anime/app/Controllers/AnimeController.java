package ru.anime.app.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.UserAnime;
import ru.anime.app.Services.AnimeService;
import ru.anime.app.Services.UserAnimeService;

@Controller
@RequiredArgsConstructor
public class AnimeController {

//    @GetMapping("/")
//    public String animeList(Model model) {
//        model.addAttribute("anime_list", animeService.getAnimeList());
//        return "home";
//    }
//

}
