package ru.anime.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import ru.anime.app.Services.AnimeService;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AppApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnimeService.class, args);
	}
}
