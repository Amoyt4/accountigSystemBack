package ru.lukin.spring.nic_zadacha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lukin.spring.nic_zadacha.model.MyContract;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MyContractRepository extends JpaRepository<MyContract, Long> {

    @Query("SELECT c FROM MyContract c WHERE " +
            "(c.plannedStartDate BETWEEN :startDate AND :endDate) OR " +
            "(c.plannedEndDate BETWEEN :startDate AND :endDate)")
    List<MyContract> findContractsByPlannedPeriod(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );
    Optional<MyContract> findByName(String name);
}