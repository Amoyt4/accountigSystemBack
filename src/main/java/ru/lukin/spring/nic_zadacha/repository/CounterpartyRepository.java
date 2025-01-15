package ru.lukin.spring.nic_zadacha.repository;

import ru.lukin.spring.nic_zadacha.model.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterpartyRepository extends JpaRepository<Counterparty, Long> {
}