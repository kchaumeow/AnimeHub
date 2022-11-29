package ru.anime.app.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anime.app.Models.*;
import ru.anime.app.Services.*;

import java.security.Principal;
import java.util.List;

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
    public String searchAnime(@ModelAttribute("search_anime") Anime anime, Model model) {
        model.addAttribute("found_anime", animeService.searchAnimeList(anime.getTitle()));
        model.addAttribute("search_anime", new Anime());
        String s = anime.getTitle();
        model.addAttribute("query", s);
        return "search_anime";
    }

    @GetMapping("/search/{genre_name}")
    public String searchAnime(@ModelAttribute("search_anime") Anime anime, @PathVariable("genre_name") String genre_name, Model model){
        model.addAttribute("found_anime", animeGenreService.getAnimeGenreListByAnimeAndGenre(anime.getTitle(),genre_name));
        model.addAttribute("search_anime", new Anime());
        String s=anime.getTitle();
        model.addAttribute("query", s);
        model.addAttribute("genre_name", genre_name);
        return "search_anime";
    }

    @GetMapping("/genres")
    public String Genre(@ModelAttribute("user") User user, Model model){
        model.addAttribute("genreList",genreService.getGenreList());
        if(user!=null) model.addAttribute("useranimeList", user.getUserAnimeList());
        model.addAttribute("search_anime", new Anime());
        return "genres";
    }

    @GetMapping("/genres/{genre_name}")
    public String genreAnime(@ModelAttribute("user") User user,@PathVariable("genre_name") String genre_name,Model model){
        return genreAnimePage(user,genre_name,1,model);
    }

    @GetMapping("/genres/{genre_name}/{currentPage}")
    public String genreAnimePage(@ModelAttribute("user") User user,@PathVariable("genre_name") String genre_name,
                             @PathVariable("currentPage") int currentPage,Model model){

        Page<AnimeGenre> animeGenrePage=animeGenreService.getAnimeGenreListByGenreNamePage(genre_name,currentPage);
        List<AnimeGenre> animeGenreList=animeGenrePage.getContent();
        int totalItems= (int) animeGenrePage.getTotalElements();
        int totalPages=animeGenrePage.getTotalPages();

        model.addAttribute("animegenreList",animeGenreList);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);

        if(user!=null) model.addAttribute("useranimeList",user.getUserAnimeList());
        model.addAttribute("genre_name", genre_name);
        model.addAttribute("search_anime",new Anime());
        return "genres_anime";
    }

}
