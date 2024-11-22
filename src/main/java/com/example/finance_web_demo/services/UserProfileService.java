package com.example.finance_web_demo.services;

import com.example.finance_web_demo.models.Account;
import com.example.finance_web_demo.models.UserProfile;
import com.example.finance_web_demo.repository.AccountRepository;
import com.example.finance_web_demo.repository.UserProfileRepository;
import com.example.finance_web_demo.util.profile.ProfileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserProfileService {
    public final UserProfileRepository userProfileRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository, AccountRepository accountRepository) {
        this.userProfileRepository = userProfileRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    @Transactional(readOnly = true)
    public UserProfile findById(long id) {
        return userProfileRepository.findById(id).orElse(null);
    }


    public void update(long id, UserProfile updatedUserProfile) {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(id);
        if (optionalUserProfile.isEmpty()) {
            throw new ProfileNotFoundException();
        }
        UserProfile updatableUserProfile = optionalUserProfile.get();
        update(updatedUserProfile, updatableUserProfile);
        userProfileRepository.save(updatableUserProfile);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public void createAccount(UserProfile userProfile) {
        Account account = new Account();
        accountRepository.save(account);
        userProfile.getAccounts().add(account);
    }

    public void deleteAccount(long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            accountRepository.delete(account);
        }
    }

    private void update(UserProfile updatedUserProfile, UserProfile updatableUserProfile) {
        if (!Objects.equals(updatableUserProfile.getFirstName(), updatedUserProfile.getFirstName()))
            updatableUserProfile.setFirstName(updatedUserProfile.getFirstName());
        if (!Objects.equals(updatableUserProfile.getLastName(), updatedUserProfile.getLastName()))
            updatableUserProfile.setLastName(updatedUserProfile.getLastName());
        if (!Objects.equals(updatableUserProfile.getAddress(), updatedUserProfile.getAddress()))
            updatableUserProfile.setAddress(updatedUserProfile.getAddress());
        if (!Objects.equals(updatableUserProfile.getDateOfBirth(), updatedUserProfile.getDateOfBirth()))
            updatableUserProfile.setDateOfBirth(updatedUserProfile.getDateOfBirth());
        if (!Objects.equals(updatableUserProfile.getPhone(), updatedUserProfile.getPhone()))
            updatableUserProfile.setPhone(updatedUserProfile.getPhone());

    }

    public void checkAccountExists(UserProfile userProfile) {
        System.out.println(userProfile.getAccounts());
    }
}
