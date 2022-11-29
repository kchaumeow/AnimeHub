package ru.anime.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeDTO {
    private Long mal_id;
    private AnimeImagesDTO images;
    private String title;
    private String title_english;
    private String title_japanese;
    private String TV;
    private int episodes;
    private String status;
    private String duration;
    private String rating;
    private String synopsis;
    private String season;
    private int year;
    //private List<AnimeStudioDTO> studios;
    private List<AnimeGenreDTO> genres;
    private AnimeAiredDTO aired;

}

