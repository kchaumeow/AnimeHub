package ru.anime.app.Controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.anime.app.Models.Anime;
import ru.anime.app.Models.User;
import ru.anime.app.Services.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AnimeService animeService;

    private final DatabaseService databaseService;
    @ModelAttribute("user")
    public User userForAll(Principal principal) {
        return userService.getUserByPrincipal(principal);
    }

    @GetMapping("/")
    public String Home(Principal principal,Model model){
        return HomePage(principal,1,model);
    }
    @GetMapping("/home/{currentPage}")
    public String HomePage(Principal principal, @PathVariable("currentPage") int currentPage, Model model) {
        //databaseService.updateAnimeGenreTable();
        Page<Anime> animePage=animeService.getPage(currentPage);
        int totalPages=animePage.getTotalPages();
        int totalItems= (int) animePage.getTotalElements();
        List<Anime> animeList=animePage.getContent();

        model.addAttribute("anime_list", animeList);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("currentPage",currentPage);

        model.addAttribute("search_anime",new Anime());
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("userForm") User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
}
