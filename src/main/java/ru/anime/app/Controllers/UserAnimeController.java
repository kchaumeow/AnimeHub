package ru.anime.app.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anime.app.Models.Anime;
import ru.anime.app.Models.User;
import ru.anime.app.Models.UserAnime;
import ru.anime.app.Services.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserAnimeController {
    private final UserAnimeService userAnimeService;
    private final AnimeService animeService;
    private  final UserService userService;
    private final AnimeGenreService animeGenreService;
    private final AnimeApiService animeApiService;

    @ModelAttribute("user")
    public User userForAll(Principal principal){
        if(principal==null) return null;
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping("/auth_home")
    public String AuthHome(@ModelAttribute("user") User user,Model model){
        return AuthHomePage(user,1,model);
    }

    @GetMapping("/auth_home/{currentPage}")
    public String AuthHomePage(@ModelAttribute("user") User user, @PathVariable("currentPage") int currentPage, Model model){

        Page<Anime> animePage=animeService.getPage(currentPage);
        int totalPages=animePage.getTotalPages();
        int totalItems= (int) animePage.getTotalElements();
        List<Anime> animeList=animePage.getContent();

        model.addAttribute("useranime_list",user.getUserAnimeList());
        model.addAttribute("anime_list", animeList);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("currentPage",currentPage);


        model.addAttribute("search_anime",new Anime());

        return "auth_home";
    }

    @GetMapping("/anime_info/{id}")
    public String animeInfo(@PathVariable Long id, Model model,@ModelAttribute("user") User user) {
        model.addAttribute("anime", animeService.getAnime(id));
        model.addAttribute("useranime",new UserAnime());
        model.addAttribute("useranime1", userAnimeService.readUserAnime(user,animeService.getAnime(id)));
        return "anime_info";
    }

    @PostMapping("/anime_info/{id}/change_status")
    public String addAnimeToUser(@PathVariable Long id,
                                 @ModelAttribute("useranime") UserAnime userAnime,
                                 Principal principal){
        User user=userService.getUserByEmail(principal.getName());
        userAnime.setAnime(animeService.getAnime(id));
        userAnime.setUser(user);
        if(!userAnimeService.isExist(userAnime)){
            userAnimeService.createUserAnime(userAnime);
        }
        else if(userAnime.getStatus().equals("notadded")){
            userAnimeService.deleteUserAnime(userAnime);
        }
        else userAnimeService.updateUserAnime(userAnime);
        return "redirect:/anime_info/"+id;
    }

    @GetMapping("/account")
    public String AccountPage(@ModelAttribute("user") User user,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("NAME",username);
        model.addAttribute("anime_list", user.getUserAnimeList());
        model.addAttribute("search_anime",new Anime());
        return "account";
    }
    @GetMapping("/search/mylist")
    public String searchAnime(@ModelAttribute("search_anime") Anime anime, Model model){
        model.addAttribute("found_anime", userAnimeService.searchUserAnimeList(anime.getTitle()));
        model.addAttribute("search_anime", new Anime());
        String s=anime.getTitle();
        model.addAttribute("query", s);
        return "search_anime";
    }
}
