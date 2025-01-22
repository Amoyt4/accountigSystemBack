package ru.lukin.spring.nic_zadacha.service;

import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lukin.spring.nic_zadacha.repository.AppUserRepository;
import ru.lukin.spring.nic_zadacha.security.domain.AppUser;
import ru.lukin.spring.nic_zadacha.security.domain.UserPrincipal;

@Service
public class UserPrincipalService implements UserDetailsService {

    @Resource
    private AppUserRepository appUserRepository;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return UserPrincipal.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(appUser.getRoles())
                .build();
    }
}
