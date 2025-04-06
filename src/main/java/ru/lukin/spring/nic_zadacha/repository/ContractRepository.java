package ru.lukin.spring.nic_zadacha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lukin.spring.nic_zadacha.model.Contract;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query(value = "SELECT c FROM Contract c WHERE (c.plannedStartDate BETWEEN :startDate AND :endDate) OR (c.plannedEndDate BETWEEN :startDate AND :endDate)")
    List<Contract> findContractsByPlannedPeriod(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    Optional<Contract> findByName(String name);
}