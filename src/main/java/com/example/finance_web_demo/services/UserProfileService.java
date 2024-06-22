package com.example.finance_web_demo.services;

import com.example.finance_web_demo.models.Account;
import com.example.finance_web_demo.models.UserProfile;
import com.example.finance_web_demo.repository.AccountRepository;
import com.example.finance_web_demo.repository.UserProfileRepository;
import com.example.finance_web_demo.util.profile.ProfileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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


    public void update(long id,UserProfile userProfile) {
        Optional<UserProfile> oldProfile = userProfileRepository.findById(id);
        if (oldProfile.isEmpty()) {
            throw new ProfileNotFoundException();
        }
        userProfile.setId(id);
        userProfileRepository.save(userProfile);
    }

    public void createAccount(UserProfile userProfile) {
        Account account = new Account();
        account.setUserProfile(userProfile);
        accountRepository.save(account);
        userProfile.setAccount(account);
    }
}
