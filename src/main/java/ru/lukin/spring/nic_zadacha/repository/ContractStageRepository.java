package ru.lukin.spring.nic_zadacha.repository;

import ru.lukin.spring.nic_zadacha.model.ContractStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractStageRepository extends JpaRepository<ContractStage, Long> {
}