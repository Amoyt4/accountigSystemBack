package ru.lukin.spring.nic_zadacha.security.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthDto {
    String username;
    String password;
}
