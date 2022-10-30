package ru.anime.app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.anime.app.Models.User;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="anime_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anime {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="age_restriction")
    private Integer age_restriction;

    @Column(name="stat")
    private String stat;

    @Column(name="first_release")
    private String first_release;
    @Column(name="last_release")
    private String last_release;

    @Column(name="episode_num")
    private Integer episode_num;
    @Column(name="season_num")
    private Integer season_num;
    @Column(name="episode_duration")
    private Integer episode_duration;

    @Column(name="script")
    private String description;

//    @OneToMany
//    @JoinColumn
//    List<UserAnime> userAnimeList;
}
