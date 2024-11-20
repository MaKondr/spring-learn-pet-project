package com.example.finance_web_demo.services;

import com.example.finance_web_demo.models.User;
import com.example.finance_web_demo.models.UserProfile;
import com.example.finance_web_demo.repository.RoleRepository;
import com.example.finance_web_demo.repository.UserProfileRepository;
import com.example.finance_web_demo.repository.UserRepository;
import com.example.finance_web_demo.util.user.UserNotCreatedException;
import com.example.finance_web_demo.util.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserProfileRepository profileRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void createUser(User user) {
        if (hasFindUserByUsername(user.getUsername()))
            throw new UserNotCreatedException("Username already exists");
        if (hasFindUserByEmail(user.getEmail()))
            throw new UserNotCreatedException("Email already exists");
        enrichUser(user);
        userRepository.save(user);
    }

    //TODO Настроить security на смену username при вызове updateUser()
    public void updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new UserNotFoundException();
        User updatableUser = optionalUser.get();
        updateUser(updatedUser, updatableUser);
        userRepository.save(updatableUser);

    }

//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long userId) {
        /*User user = */
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
//        profileRepository.deleteById(user.getUserProfile().getId());
        userRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return userRepository.findFirstByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByUsernameCheck(String username) {
        return userRepository.findFirstByUsername(username);
    }


    @Transactional(readOnly = true)
    public Optional<User> findUserByEmailCheck(String email) {
        return userRepository.findFirstByEmail(email);
    }


    private boolean hasFindUserByUsername(String username) {
        return userRepository.findFirstByUsername(username).isPresent();
    }

    private boolean hasFindUserByEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    private void enrichUser(User user) {
        encryptPassword(user);
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER").orElse(null)));
        user.setEnabled(true);
        createProfile(user);
    }

    private void updateUser(User updatedUser, User updatableUser) {
        if (!updatableUser.getUsername().equals(updatedUser.getUsername()))
            updatableUser.setUsername(updatedUser.getUsername());

        if (!updatableUser.getEmail().equals(updatedUser.getEmail()))
            updatableUser.setEmail(updatedUser.getEmail());

        if (!updatableUser.getPassword().equals(updatedUser.getPassword()))
            updatableUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

    }

    private void createProfile(User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        profileRepository.save(userProfile);
        user.setUserProfile(userProfile);
    }


    private void encryptPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}
