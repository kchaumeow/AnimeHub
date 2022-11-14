package ru.anime.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeImagesDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnimeImageByType {
        private String image_url;
        private String small_image_url;
        private String large_image_url;
    }

    private AnimeImageByType jpg;
    private AnimeImageByType webp;
}
