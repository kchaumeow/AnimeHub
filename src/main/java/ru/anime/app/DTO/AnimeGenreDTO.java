package ru.anime.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeGenreDTO {
    private Long mal_id;
    private String name;
}