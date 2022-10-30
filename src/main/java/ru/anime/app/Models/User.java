package ru.anime.app.Models;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.*;
import java.time.LocalDateTime;

@Entity
@Table(name="usr")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "password")
    private String password;

//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<UserAnime> userAnimeList=new ArrayList<>();

//    @ManyToMany
//    @JoinTable(name="user_anime",
//    joinColumns = @JoinColumn(name = "userid" ),
//    inverseJoinColumns = @JoinColumn(name = "animeid"))
//    private List<Anime> AnimeList;


/*
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}

