package ru.anime.app.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.anime.app.Models.Anime;
import ru.anime.app.Reposits.AnimeRepository;
import ru.anime.app.Reposits.UserRepository;
import ru.anime.app.Models.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }
    public List<User> allUsers(){
        return userRepository.findAll();
    }
    public boolean createUser(User user){
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        log.info("Saving new User with email: {}", email);
        return true;
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserByPrincipal(Principal principal){
        if(principal==null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

}
