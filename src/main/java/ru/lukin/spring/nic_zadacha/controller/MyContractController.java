package ru.lukin.spring.nic_zadacha.controller;

import ru.lukin.spring.nic_zadacha.DTO.MyContractDTO;
import ru.lukin.spring.nic_zadacha.service.MyContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-contracts")
public class MyContractController {

    @Autowired
    private MyContractService myContractService;

    @GetMapping
    public List<MyContractDTO> getAllMyContracts() {
        return myContractService.getAllMyContracts();
    }

    @GetMapping("/{id}")
    public MyContractDTO getMyContractById(@PathVariable Long id) {
        return myContractService.getMyContractById(id).orElse(null);
    }

    @PostMapping
    public MyContractDTO createMyContract(@RequestBody MyContractDTO myContractDTO) {
        return myContractService.createMyContract(myContractDTO);
    }

    @PutMapping("/{id}")
    public MyContractDTO updateMyContract(@PathVariable Long id, @RequestBody MyContractDTO myContractDTO) {
        myContractDTO.setId(id);
        return myContractService.updateMyContract(myContractDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMyContract(@PathVariable Long id) {
        myContractService.deleteMyContract(id);
    }
}