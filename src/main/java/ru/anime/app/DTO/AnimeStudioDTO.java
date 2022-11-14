package ru.anime.app.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeStudioDTO {
    private int mal_id;
    private String type;
    private String name;
    private String url;
}
