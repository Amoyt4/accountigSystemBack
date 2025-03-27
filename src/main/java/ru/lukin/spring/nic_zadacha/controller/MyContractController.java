package ru.lukin.spring.nic_zadacha.controller;

import lombok.AllArgsConstructor;
import ru.lukin.spring.nic_zadacha.DTO.MyContractDTO;
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
public class MyContractController {

    private final ContractService contractService;

    @GetMapping
    public List<MyContractDTO> getAllMyContracts() {
        return contractService.getAllMyContracts();
    }

    @GetMapping("/{id}")
    public MyContractDTO getMyContractById(@PathVariable Long id) {
        return contractService.getMyContractById(id).orElse(null);
    }

    @PostMapping
    public MyContractDTO createMyContract(@RequestBody MyContractDTO myContractDTO) {
        return contractService.createMyContract(myContractDTO);
    }

    @PutMapping("/{id}")
    public MyContractDTO updateMyContract(@PathVariable Long id, @RequestBody MyContractDTO myContractDTO) {
        myContractDTO.setId(id);
        return contractService.updateMyContract(myContractDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMyContract(@PathVariable Long id) {
        contractService.deleteMyContract(id);
    }
}