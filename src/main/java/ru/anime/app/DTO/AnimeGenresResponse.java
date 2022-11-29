package ru.anime.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeGenresResponse {
    private List<AnimeGenreDTO> data;
}
