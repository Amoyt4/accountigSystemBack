package ru.lukin.spring.nic_zadacha.controller.jwt;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lukin.spring.nic_zadacha.security.domain.AuthDto;
import ru.lukin.spring.nic_zadacha.security.util.JwtUtil;
import ru.lukin.spring.nic_zadacha.service.UserPrincipalService;

@RestController
@RequestMapping
public class AuthController {

    @Resource
    private UserPrincipalService userPrincipalService;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<String>createJwtToken(@RequestBody AuthDto authDto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword())
        );

        return ResponseEntity.ok(jwtUtil.generateToken(userPrincipalService.loadUserByUsername(authDto.getUsername())));
    }

}
