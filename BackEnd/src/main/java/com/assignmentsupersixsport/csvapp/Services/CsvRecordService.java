package com.assignmentsupersixsport.csvapp.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignmentsupersixsport.csvapp.Models.CsvRecord;
import com.assignmentsupersixsport.csvapp.Repositories.CsvRecordRepository;

@Service
public class CsvRecordService {
    private final CsvRecordRepository csvRecordRepository;

    public CsvRecordService(CsvRecordRepository csvRecordRepository) {
        this.csvRecordRepository = csvRecordRepository;
    }

    public void uploadCsv(MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    CsvRecord csvRecord = new CsvRecord();
                    csvRecord.setCreditScore(Integer.parseInt(fields[0]));
                    csvRecord.setCreditLines(Integer.parseInt(fields[1]));
                    csvRecordRepository.save(csvRecord);
                } else {
                    throw new IllegalArgumentException("Invalid CSV Format");
                }
            }
        }
    }

    public double calculateSubscriptionPrice(int creditScore, int creditLines, double basePrice,
            double pricePerCreditLine, double pricePerCreditScorePoint) {
        return basePrice + (pricePerCreditLine * creditLines) + (pricePerCreditScorePoint * creditScore);
    }

    public Page<CsvRecord> getAllCsvRecords(int page, int size) {
        return csvRecordRepository.findAll(PageRequest.of(page, size));
    }

}
