package ru.lukin.spring.nic_zadacha.controller.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/data-excel")
public class DataExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping
    public ResponseEntity<byte[]> dataExcel(@RequestBody DateRangeRequest dateRangeRequest) {
        try {
            LocalDate startDate = LocalDate.parse(dateRangeRequest.getDate1());
            LocalDate endDate = LocalDate.parse(dateRangeRequest.getDate2());

            byte[] excelFile = excelService.createExcelFile(startDate, endDate);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=contracts_report.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(excelFile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}