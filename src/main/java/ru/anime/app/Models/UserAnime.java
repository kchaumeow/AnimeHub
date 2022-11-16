package ru.anime.app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user_anime")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnime {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name="user")
    private User user;

//    @Column(name="userid")
//    private Long userid;
//    @Column(name="animeid")
//    private Long animeid;

    @NonNull
    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name="anime")
    private Anime anime;

    @NonNull
    @Column(name="stat")
    private String status="not added";

}
