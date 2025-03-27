package ru.lukin.spring.nic_zadacha.security.domain;

import jakarta.persistence.Table;

@Table(name = "app_user_role")
public enum Role {
    USER, ADMIN
}
