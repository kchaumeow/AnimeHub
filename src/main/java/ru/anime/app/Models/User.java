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
    @Column(name = "username")
    private String name;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<UserAnime> userAnimeList=new ArrayList<>();

    public void removeAnime(Anime anime){
        UserAnime userAnime=userAnimeList.stream().filter(userAnime1 -> userAnime1.getAnime().equals(anime))
                .findAny().orElse(null);
        if(userAnime!=null) userAnimeList.remove(userAnime);
    }

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

