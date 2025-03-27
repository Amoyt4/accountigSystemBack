package ru.lukin.spring.nic_zadacha.security.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUserDto {
    String username;
    String password;
    List<Role> roles;
}
