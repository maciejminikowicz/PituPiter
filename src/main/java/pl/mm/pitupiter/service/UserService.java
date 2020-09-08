package pl.mm.pitupiter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mm.pitupiter.model.User;
import pl.mm.pitupiter.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }


    public void registerUser(User user) {
        userRepository.save(user);
    }

}
