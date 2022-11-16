package ru.anime.app.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.anime.app.Services.AnimeService;
@Controller
@RequiredArgsConstructor
public class MainController {
    private final AnimeService animeService;



}