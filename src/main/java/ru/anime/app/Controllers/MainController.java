package ru.anime.app.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anime.app.Models.User;
import ru.anime.app.Services.AnimeService;
import ru.anime.app.Services.UserService;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final AnimeService animeService;
    private final UserService userService;





}