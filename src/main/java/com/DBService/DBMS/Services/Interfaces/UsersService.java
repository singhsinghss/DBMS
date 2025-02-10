package com.DBService.DBMS.Services.Interfaces;

import com.DBService.DBMS.Models.Flights;
import com.DBService.DBMS.Models.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    Users registerNewUser(Users user);
    Users getUserById(Long userId);
    Users updateUser(Long userId,Users user);
    void deleteById(Long userId);
    List<Users> getUsers();
}
