package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class LoanCalculator {
    //Instance Variables
    private BigDecimal initialPrinciple;
    private BigDecimal currentPrinciple;
    private BigDecimal interestRate;

    //Constructor
    /**
     *
     * @param initialPrinciple The principle loan amount
     * @param interestRate APR as a whole number or decimal
     */
    public LoanCalculator(BigDecimal initialPrinciple, BigDecimal interestRate){
        this.initialPrinciple = initialPrinciple;
        currentPrinciple = initialPrinciple;

        if (interestRate.compareTo(BigDecimal.ONE)<0) {
            this.interestRate = interestRate;
        } else {
            this.interestRate = interestRate.divide(new BigDecimal("100"), 10, RoundingMode.HALF_EVEN);
        }
    }

    //Getters
    public BigDecimal getInitialPrinciple() {
        return initialPrinciple;
    }
    public BigDecimal getCurrentPrinciple() {
        return currentPrinciple;
    }
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    //Methods
    public BigDecimal interestRatePerMonth() {
        return getInterestRate().divide(new BigDecimal("12"), 10, RoundingMode.HALF_EVEN);
    }
    public BigDecimal interestAccrued() {
        return currentPrinciple.multiply(interestRatePerMonth());
    }
    public void makePayment (BigDecimal principalPayment) {
        currentPrinciple = currentPrinciple.subtract(principalPayment);
    }
    public BigDecimal principlePayment(BigDecimal monthlyPayment) {
        if (monthlyPayment.compareTo(getCurrentPrinciple().add(interestAccrued()))>=0) {
            return getCurrentPrinciple();
        }
        return monthlyPayment.subtract(interestAccrued());
    }
    public String amortizationSchedule(BigDecimal monthlyPayment, String header) {
        int month = 0;

        String schedule = header==null ? "" : header;
        schedule += "\n  | Beginning | Interest | Principle | Remaining\n";

        while ((getCurrentPrinciple().setScale(4, RoundingMode.DOWN)).compareTo(BigDecimal.ZERO) >0) {
            month++;

            schedule += "\n" + month;
            schedule += " | $" + String.format("%.2f", getCurrentPrinciple());
            schedule += " | $" + String.format("%.2f", interestAccrued());
            schedule += " | $" + String.format("%.2f", principlePayment(monthlyPayment));

            makePayment(principlePayment(monthlyPayment));

            schedule += " | $" + String.format("%.2f", getCurrentPrinciple());
        }
        return schedule;
    }

}

