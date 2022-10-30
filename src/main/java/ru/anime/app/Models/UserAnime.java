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
    @Column(name="userid")
    private Long userid;
    @NonNull
    @Column(name="animeid")
    private Long animeid;
    @NonNull
    @Column(name="stat")
    private String status="not added";


//    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
//    @JoinColumn
//    private User user;
//    @OneToOne
//    private Anime anime;
}
