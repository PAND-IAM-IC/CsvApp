package com.assignmentsupersixsport.csvapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignmentsupersixsport.csvapp.Models.CsvRecord;

@Repository
public interface CsvRecordRepository extends JpaRepository<CsvRecord, Long> {

}
