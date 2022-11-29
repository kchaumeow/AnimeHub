package ru.anime.app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="anime_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anime {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="title_english")
    private String title_english;

    @Column(name="title_japanese")
    private String title_japanese;

    @Column(name="type")
    private String TV;

    @Column(name="episodes")
    private int episodes;

    @Column(name="status")
    private String status;

    @Column(name = "duration")
    private String duration;

    @Column(name="rating")
    private String rating;

    @Column(name="synopsis", length = 5000)
    @Type(type = "org.hibernate.type.TextType")
    private String synopsis;

    @Column(name="season")
    private String season;

    @Column(name="year")
    private int year;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL)
    private List<AnimeGenre> animeGenres;

    @Column(name = "webp")
    private String webp;
    @Column(name="jpg")
    private String jpg;

    @Column(name = "first_release")
    private String from;

    @Column(name = "last_release")
    private String to;

    public Anime(Long mal_id,
                 String title,
                 String title_english,
                 String title_japanese,
                 String tv,
                 int episodes,
                 String status,
                 String duration,
                 String rating,
                 String synopsis,
                 String season,
                 int year,
                 String webp,
                 String jpg,
                 String from,
                 String to) {
        this.id=mal_id;
        this.title=title;
        this.title_english=title_english;
        this.title_japanese=title_japanese;
        this.TV=tv;
        this.episodes=episodes;
        this.status=status;
        this.duration=duration;
        this.rating=rating;
        this.synopsis=synopsis;
        this.season=season;
        this.year=year;
        this.webp=webp;
        this.jpg=jpg;
        this.from=from;
        this.to=to;
    }

    // private AnimeImagesDTO images;
    //private List<AnimeStudioDTO> studios;
    //private AnimeAiredDTO animeAiredDTO;
}
