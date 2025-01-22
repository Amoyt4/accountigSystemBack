package ru.lukin.spring.nic_zadacha.controller.jwt;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lukin.spring.nic_zadacha.security.domain.AuthDto;
import ru.lukin.spring.nic_zadacha.service.AppUserSerice;

@RestController
@RequestMapping
public class RegisterController {

    @Resource
    private AppUserSerice appUserSerice;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthDto authDto) {
        appUserSerice.registerAppUser(authDto);
        return ResponseEntity.ok("OK");
    }
}
