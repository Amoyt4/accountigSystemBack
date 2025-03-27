package ru.lukin.spring.nic_zadacha.repository;

import org.springframework.stereotype.Repository;
import ru.lukin.spring.nic_zadacha.model.SubContract;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SubContractRepository extends JpaRepository<SubContract, Long> {
}