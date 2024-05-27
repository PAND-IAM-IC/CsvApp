package com.assignmentsupersixsport.csvapp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CsvRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int creditScore;
    private int creditLines;
    private double basePrice;
    private double pricePerCreditLine;
    private double pricePerCreditScorePoint;

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    public CsvRecord(long id, int creditScore, int creditLines, double basePrice, double pricePerCreditLine,
            double pricePerCreditScorePoint) {
        this.id = id;
        this.creditScore = creditScore;
        this.creditLines = creditLines;
        this.basePrice = basePrice;
        this.pricePerCreditLine = pricePerCreditLine;
        this.pricePerCreditScorePoint = pricePerCreditScorePoint;
    }

    public CsvRecord() {
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return int return the creditScore
     */
    public int getCreditScore() {
        return creditScore;
    }

    /**
     * @param creditScore the creditScore to set
     */
    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    /**
     * @return int return the creditLines
     */
    public int getCreditLines() {
        return creditLines;
    }

    /**
     * @param creditLines the creditLines to set
     */
    public void setCreditLines(int creditLines) {
        this.creditLines = creditLines;
    }

    /**
     * @return double return the basePrice
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * @param basePrice the basePrice to set
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * @return double return the pricePerCreditLine
     */
    public double getPricePerCreditLine() {
        return pricePerCreditLine;
    }

    /**
     * @param pricePerCreditLine the pricePerCreditLine to set
     */
    public void setPricePerCreditLine(double pricePerCreditLine) {
        this.pricePerCreditLine = pricePerCreditLine;
    }

    /**
     * @return double return the pricePerCreditScorePoint
     */
    public double getPricePerCreditScorePoint() {
        return pricePerCreditScorePoint;
    }

    /**
     * @param pricePerCreditScorePoint the pricePerCreditScorePoint to set
     */
    public void setPricePerCreditScorePoint(double pricePerCreditScorePoint) {
        this.pricePerCreditScorePoint = pricePerCreditScorePoint;
    }

}
