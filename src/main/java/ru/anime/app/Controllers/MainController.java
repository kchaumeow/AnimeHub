package ru.anime.app.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.anime.app.Reposits.UserRepository;
import ru.anime.app.Services.UserService;
import ru.anime.app.Models.User;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserRepository userRepository;
    @GetMapping("/")
    public String HomePage(){
        return "home";
    }
    private final UserService userService;
}