package ru.lukin.spring.nic_zadacha.controller.excel;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lukin.spring.nic_zadacha.model.ContractStage;
import ru.lukin.spring.nic_zadacha.model.MyContract;
import ru.lukin.spring.nic_zadacha.repository.MyContractRepository;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContractExcelService {

    private final MyContractRepository myContractRepository;

    public byte[] createExcelFile(String contractName) throws Exception {
        Optional<MyContract> contractOptional = myContractRepository.findByName(contractName);

        if (contractOptional.isEmpty()) {
            throw new IllegalArgumentException("Contract not found");
        }

        MyContract contract = contractOptional.get();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Contract Stages");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Stage Name");
        headerRow.createCell(1).setCellValue("Planned Start Date");
        headerRow.createCell(2).setCellValue("Planned End Date");
        headerRow.createCell(3).setCellValue("Actual Start Date");
        headerRow.createCell(4).setCellValue("Actual End Date");
        headerRow.createCell(5).setCellValue("Amount");
        headerRow.createCell(6).setCellValue("Material Costs Plan");
        headerRow.createCell(7).setCellValue("Material Costs Actual");
        headerRow.createCell(8).setCellValue("Salary Costs Plan");
        headerRow.createCell(9).setCellValue("Salary Costs Actual");

        int rowNum = 1;
        for (ContractStage stage : contract.getStages()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(stage.getName());
            row.createCell(1).setCellValue(stage.getPlannedStartDate().toString());
            row.createCell(2).setCellValue(stage.getPlannedEndDate().toString());
            row.createCell(3).setCellValue(stage.getActualStartDate() != null ? stage.getActualStartDate().toString() : "");
            row.createCell(4).setCellValue(stage.getActualEndDate() != null ? stage.getActualEndDate().toString() : "");
            row.createCell(5).setCellValue(String.valueOf(stage.getAmount()));
            row.createCell(6).setCellValue(String.valueOf(stage.getMaterialCostsPlan()));
            row.createCell(7).setCellValue(String.valueOf(stage.getMaterialCostsActual()));
            row.createCell(8).setCellValue(String.valueOf(stage.getSalaryCostsPlan()));
            row.createCell(9).setCellValue(String.valueOf(stage.getSalaryCostsActual()));
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }
}