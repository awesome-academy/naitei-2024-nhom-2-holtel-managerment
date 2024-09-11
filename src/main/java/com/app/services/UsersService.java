package com.app.services;

import com.app.dtos.UserDTO;
import com.app.dtos.UserInfoDTO;
import com.app.dtos.UserRegistrationDTO;
import com.app.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersService {
    User saveUser(UserRegistrationDTO registrationDTO);

    User findUserByEmail(String email);

    User findUserById(Integer id);

    Page<User> findPaginatedUsers(Pageable pageable);
    void updateLoggedInUser(UserDTO userDTO);

    User getCurrentUser();

    public int countUsers();

    List<User> findAllUsers();

    void updateUser(Integer id, UserInfoDTO userDTO);

    void deleteUser(Integer id);

    UserInfoDTO convertToDTO(User user);
    User convertToEntity(UserInfoDTO userDTO);

    void saveUserInfo(UserInfoDTO userDTO);

    List<User> findAll();
}
