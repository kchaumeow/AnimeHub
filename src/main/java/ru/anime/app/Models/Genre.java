package ru.anime.app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="genre_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre{
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;


}
