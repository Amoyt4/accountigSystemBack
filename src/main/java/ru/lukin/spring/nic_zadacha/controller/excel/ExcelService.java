package ru.lukin.spring.nic_zadacha.controller.excel;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.lukin.spring.nic_zadacha.model.Contract;
import ru.lukin.spring.nic_zadacha.model.SubContract;
import ru.lukin.spring.nic_zadacha.repository.ContractRepository;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class ExcelService {

    private final ContractRepository contractRepository;

    public byte[] createExcelFile(LocalDate startDate, LocalDate endDate) throws Exception {
        List<Contract> contracts = contractRepository.findContractsByPlannedPeriod(startDate, endDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Contracts");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Contract Name");
        headerRow.createCell(1).setCellValue("Contract Type");
        headerRow.createCell(2).setCellValue("Planned Start Date");
        headerRow.createCell(3).setCellValue("Planned End Date");
        headerRow.createCell(4).setCellValue("Amount");
        headerRow.createCell(5).setCellValue("Main Contract (if subcontract)");

        int rowNum = 1;
        for (Contract contract : contracts) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(contract.getName());
            row.createCell(1).setCellValue(contract.getContractType());
            row.createCell(2).setCellValue(contract.getPlannedStartDate().format(formatter));
            row.createCell(3).setCellValue(contract.getPlannedEndDate().format(formatter));
            row.createCell(4).setCellValue(String.valueOf(contract.getAmount()));
            row.createCell(5).setCellValue("Main");

            for (SubContract subContract : contract.getSubContracts()) {
                Row subRow = sheet.createRow(rowNum++);
                subRow.createCell(0).setCellValue(subContract.getName());
                subRow.createCell(1).setCellValue(subContract.getContractType());
                subRow.createCell(2).setCellValue(subContract.getPlannedStartDate().format(formatter));
                subRow.createCell(3).setCellValue(subContract.getPlannedEndDate().format(formatter));
                subRow.createCell(4).setCellValue(String.valueOf(subContract.getAmount()));
                subRow.createCell(5).setCellValue(contract.getName());
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }
}