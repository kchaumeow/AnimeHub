package ru.anime.app.Controllers;

import lombok.RequiredArgsConstructor;
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
    public String AuthHomePage(@ModelAttribute("user") User user, Model model){
        model.addAttribute("anime_list", animeService.getAnimeList());
        model.addAttribute("useranime_list",user.getUserAnimeList());
        model.addAttribute("search_title",new Anime());
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
        model.addAttribute("search_title",new Anime());
        return "account";
    }



//    @ModelAttribute("user")
//    public User userForAll(Principal principal){
//        return userService.getUserByPrincipal(principal);
//    }
//
//    @GetMapping("/auth_home")
//    public String AuthHomePage(@ModelAttribute("user") User user, Model model){
//        model.addAttribute("anime_list", animeService.getAnimeList());
//        model.addAttribute("useranime_list",userAnimeService.getUserAnimeList(user.getId()));
//        return "auth_home";
//    }
//
//    @GetMapping("/anime_info/{id}")
//    public String animeInfo(@PathVariable Long id, Model model,@ModelAttribute("user") User user) {
//        model.addAttribute("anime", animeService.getAnime(id));
//        model.addAttribute("useranime",new UserAnime());
//        model.addAttribute("useranime1", userAnimeService.readUserAnime(user.getId(), id));
//        return "anime_info";
//    }
//
//    @PostMapping("/anime_info/{id}/change_status")
//    public String addAnimeToUser(@PathVariable Long id,
//                                 @ModelAttribute("useranime") UserAnime userAnime,
//                                 Principal principal){
//        userAnime.setAnimeid(id);
//        userAnime.setUserid(userService.getUserByPrincipal(principal).getId());
//        if(!userAnimeService.isExist(userAnime)){
//            userAnimeService.createUserAnime(userAnime);
//        }
//        else if(userAnime.getStatus().equals("notadded")){
//            userAnimeService.deleteUserAnime(userAnime);
//        }
//        else userAnimeService.updateUserAnime(userAnime);
//        return "redirect:/anime_info/"+id;
//    }
//
//    @GetMapping("/account")
//    public String AccountPage(@ModelAttribute("user") User user,Model model){
//        model.addAttribute("anime_list", userAnimeService.selectUserAnimeList(user.getId()));
//        model.addAttribute("useranime_list",userAnimeService.getUserAnimeList(user.getId()));
//        return "account";
//    }

//    @PostMapping("/anime_info/{id}")
//    public String addAnimeToUser(@PathVariable Long id,
//                                 @RequestParam("status_select") String status,
//                                 @ModelAttribute UserAnime userAnime){
//        userAnimeService.setUserAnime(user.getId(),id,status);
//        return "anime_info";
//    }

}
