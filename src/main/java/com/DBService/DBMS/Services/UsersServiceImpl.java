package com.DBService.DBMS.Services;

import com.DBService.DBMS.Models.Users;
import com.DBService.DBMS.Repositories.UsersRepository;
import com.DBService.DBMS.Services.Interfaces.UsersService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    @Autowired
    private final UsersRepository userRepository;

    @Override
    public Users registerNewUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    @Transactional
    public Users updateUser(Long userId, Users user) {
        Optional<Users> existingUserId = userRepository.findById(userId);
        System.out.println("*****id*****: "+existingUserId);
        if (existingUserId.isPresent()) {
            Users existinguser = existingUserId.get();
            System.out.println("Before Update: " + existinguser);

            if(user.getEmail()!=null) {
                existinguser.setEmail(user.getEmail());
            }
            if(user.getPhone_number()!=null) {
                existinguser.setPhone_number(user.getPhone_number());
            }
            if(user.getUsername()!=null)
            {
                existinguser.setUsername(user.getUsername());
            }
            existinguser.setUpdated_at(LocalDateTime.now());
            return userRepository.save(existinguser);
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist!");
        }

    }


    @Override
    public void deleteById(Long user_id) {
        Optional<Users> userId = userRepository.findById(user_id);
        if (userId.isEmpty()) {
            throw new RuntimeException("user not found for user id: " + userId);
        }
        userRepository.deleteById(user_id);

    }

    @Override
    public List<Users> getUsers() {
        return userRepository.findAll();
    }

}
