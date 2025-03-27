package ru.lukin.spring.nic_zadacha.controller.excel;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/data-excel")
@AllArgsConstructor
public class DataExcelController {

    private final ExcelService excelService;

    @PostMapping
    public ResponseEntity<byte[]> dataExcel(@RequestBody DateRangeRequest dateRangeRequest) {
        try {
            Date startDate = dateRangeRequest.getDateStart();
            Date endDate = dateRangeRequest.getDateEnd();

            byte[] excelFile = excelService.createExcelFile(startDate, endDate);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=contracts_report.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(excelFile);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при создании файла".getBytes());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}