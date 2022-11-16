package ru.anime.app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="anime_genre")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "anime")
    private Anime anime;

    @OneToOne
    @JoinColumn(name="genre")
    private Genre genre;

    public AnimeGenre(Anime anime, Genre genre) {
        this.anime=anime;
        this.genre=genre;
    }
}
