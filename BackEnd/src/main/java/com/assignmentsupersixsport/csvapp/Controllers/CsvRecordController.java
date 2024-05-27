package com.assignmentsupersixsport.csvapp.Controllers;

import com.assignmentsupersixsport.csvapp.Models.CsvRecord;
import com.assignmentsupersixsport.csvapp.Services.CsvRecordService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/csv")
public class CsvRecordController {

    private final CsvRecordService csvRecordService;

    public CsvRecordController(CsvRecordService csvRecordService) {
        this.csvRecordService = csvRecordService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            csvRecordService.uploadCsv(file);
            return ResponseEntity.ok("CSV uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload CSV: " + e.getMessage());
        }
    }

    @GetMapping("/calculate-price")
    public ResponseEntity<Double> calculateSubscriptionPrice(
            @RequestParam int creditScore,
            @RequestParam int creditLines,
            @RequestParam double basePrice,
            @RequestParam double pricePerCreditLine,
            @RequestParam double pricePerCreditScorePoint) {
        double subscriptionPrice = csvRecordService.calculateSubscriptionPrice(creditScore, creditLines, basePrice,
                pricePerCreditLine, pricePerCreditScorePoint);
        return ResponseEntity.ok(subscriptionPrice);
    }

    @GetMapping("/records")
    public ResponseEntity<Page<CsvRecord>> getAllCsvRecords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CsvRecord> records = csvRecordService.getAllCsvRecords(page, size);
        return ResponseEntity.ok(records);
    }
}
