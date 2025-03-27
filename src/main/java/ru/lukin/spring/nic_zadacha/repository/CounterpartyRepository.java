package ru.lukin.spring.nic_zadacha.repository;

import org.springframework.stereotype.Repository;
import ru.lukin.spring.nic_zadacha.model.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CounterpartyRepository extends JpaRepository<Counterparty, Long> {
}