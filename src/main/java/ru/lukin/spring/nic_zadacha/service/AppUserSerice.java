package ru.lukin.spring.nic_zadacha.service;

import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lukin.spring.nic_zadacha.repository.AppUserRepository;
import ru.lukin.spring.nic_zadacha.security.domain.AppUser;
import ru.lukin.spring.nic_zadacha.security.domain.AppUserDto;
import ru.lukin.spring.nic_zadacha.security.domain.AuthDto;
import ru.lukin.spring.nic_zadacha.security.domain.Role;


import java.util.List;

@Service
public class AppUserSerice {
    @Resource
    private AppUserRepository appUserRepository;

    @Resource
    PasswordEncoder passwordEncoder;

    public void registerAppUser(AuthDto authDto) {
        authDto.setPassword(passwordEncoder.encode(authDto.getPassword()));

        AppUserDto appUserDto = AppUserDto.builder()
                .username(authDto.getUsername())
                .password(authDto.getPassword())
                .role(List.of(Role.USER))
                .build();

        appUserRepository.save(new ModelMapper().map(appUserDto, AppUser.class));
    }
}
