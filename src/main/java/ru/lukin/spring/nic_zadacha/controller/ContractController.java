package ru.lukin.spring.nic_zadacha.controller;

import lombok.AllArgsConstructor;
import ru.lukin.spring.nic_zadacha.DTO.ContractDTO;
import ru.lukin.spring.nic_zadacha.service.ContractService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/my-contracts")
@AllArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping
    public List<ContractDTO> getAllMyContracts() {
        return contractService.getAllMyContracts();
    }

    @GetMapping("/{id}")
    public ContractDTO getMyContractById(@PathVariable Long id) {
        return contractService.getMyContractById(id).orElse(null);
    }

    @PostMapping
    public ContractDTO createMyContract(@RequestBody ContractDTO contractDTO) {
        return contractService.createMyContract(contractDTO);
    }

    @PutMapping("/{id}")
    public ContractDTO updateMyContract(@PathVariable Long id, @RequestBody ContractDTO contractDTO) {
        contractDTO.setId(id);
        return contractService.updateMyContract(contractDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMyContract(@PathVariable Long id) {
        contractService.deleteMyContract(id);
    }
}