package ru.anime.app.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anime.app.Models.User;
import ru.anime.app.Services.AnimeService;
import ru.anime.app.Services.UserAnimeService;
import ru.anime.app.Services.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AnimeService animeService;
    private final UserAnimeService userAnimeService;

    @ModelAttribute("user")
    public User userForAll(Principal principal){
        return userService.getUserByPrincipal(principal);
    }
    @GetMapping("/")
    public String HomePage(Principal principal,Model model){
        model.addAttribute("anime_list", animeService.getAnimeList());
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm",new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(@ModelAttribute ("userForm") User user, Model model){
        if (!userService.createUser(user)){
            model.addAttribute("errorMessage", "Пользователь с email " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/acc")
    public String account(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("NAME",username);
        return "account";
    }
}
