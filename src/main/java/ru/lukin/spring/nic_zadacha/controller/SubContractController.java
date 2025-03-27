package ru.lukin.spring.nic_zadacha.controller;

import lombok.AllArgsConstructor;
import ru.lukin.spring.nic_zadacha.DTO.SubContractDTO;
import ru.lukin.spring.nic_zadacha.service.SubContractService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/sub-contracts")
@AllArgsConstructor
public class SubContractController {

    private final SubContractService subContractService;

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