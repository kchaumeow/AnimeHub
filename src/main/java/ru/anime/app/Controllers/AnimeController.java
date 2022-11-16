package ru.anime.app.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.Genre;
import ru.anime.app.Models.User;
import ru.anime.app.Models.UserAnime;
import ru.anime.app.Services.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeService animeService;
    private final AnimeGenreService animeGenreService;
    private final GenreService genreService;
    private final UserService userService;

    @ModelAttribute("user")
    public User userForAll(Principal principal){
        if(principal==null) return null;
        return userService.getUserByEmail(principal.getName());
    }
    @GetMapping("/search")
    public String searchAnime(@ModelAttribute("search_title") Anime anime, Model model){
        model.addAttribute("found_anime", animeService.searchAnimeList(anime.getTitle()));
        model.addAttribute("search_title", new Anime());
        String s=anime.getTitle();
        model.addAttribute("query", s);
        return "search_anime";
    }

    @GetMapping("/genre/{genre_name}")
    public String genreAnime(@PathVariable("genre_name") String genre_name, Model model){
        Genre genre=genreService.getGenreByName(genre_name);
        model.addAttribute("animegenre_list",animeGenreService.getAnimeGenreListByGenre(genre));
        model.addAttribute("genre_name", genre_name);
        model.addAttribute("search_title",new Anime());
        return "genres";
    }

}
