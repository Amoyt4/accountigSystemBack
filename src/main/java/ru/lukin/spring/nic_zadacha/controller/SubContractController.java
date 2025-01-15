package ru.lukin.spring.nic_zadacha.controller;

import ru.lukin.spring.nic_zadacha.DTO.SubContractDTO;
import ru.lukin.spring.nic_zadacha.service.SubContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sub-contracts")
public class SubContractController {

    @Autowired
    private SubContractService subContractService;

    @GetMapping
    public List<SubContractDTO> getAllSubContracts() {
        return subContractService.getAllSubContracts();
    }

    @PostMapping
    public SubContractDTO createSubContract(@RequestBody SubContractDTO subContractDTO) {
        return subContractService.createSubContract(subContractDTO);
    }

    @PutMapping("/{id}")
    public SubContractDTO updateSubContract(@PathVariable Long id, @RequestBody SubContractDTO subContractDTO) {
        subContractDTO.setId(id);
        return subContractService.updateSubContract(subContractDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSubContract(@PathVariable Long id) {
        subContractService.deleteSubContract(id);
    }
}