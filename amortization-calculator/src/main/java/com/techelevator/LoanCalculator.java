package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LoanCalculator {
    //Instance Variables
    private final BigDecimal initialPrinciple;
    private BigDecimal currentPrinciple;
    private final BigDecimal interestRate;
    private BigDecimal monthlyPayment;

    //Constructor
    /**
     *
     * @param initialPrinciple The principle loan amount
     * @param interestRate APR as a whole number or decimal
     * @param monthlyPayment How much user wants to pay each month
     */
    public LoanCalculator(BigDecimal initialPrinciple, BigDecimal interestRate, BigDecimal monthlyPayment) throws Exception {
        this.initialPrinciple = initialPrinciple;
        currentPrinciple = initialPrinciple;

        // Accept interest rate in decimal or percent format and convert automatically
        if (interestRate.compareTo(BigDecimal.ONE)<0) {
            this.interestRate = interestRate;
        } else {
            this.interestRate = interestRate.divide(new BigDecimal("100"), 10, RoundingMode.HALF_EVEN);
        }

        // Check to make sure monthlyPayment is greater than accrued interest
        if (monthlyPayment.compareTo(getInterestAccrued()) > 0) {
            this.monthlyPayment = monthlyPayment;
        } else {
            throw new Exception("Monthly payment must be more than monthly accrued interest.");
        }
    }

    /**
     *
     * @param initialPrinciple The principle loan amount
     * @param interestRate APR as a whole number or decimal
     */
    public LoanCalculator(BigDecimal initialPrinciple, BigDecimal interestRate){
        this.initialPrinciple = initialPrinciple;
        currentPrinciple = initialPrinciple;

        // Accept interest rate in decimal or percent format and convert automatically
        if (interestRate.compareTo(BigDecimal.ONE)<0) {
            this.interestRate = interestRate;
        } else {
            this.interestRate = interestRate.divide(new BigDecimal("100"), 10, RoundingMode.HALF_EVEN);
        }
    }

    protected void setMonthlyPayment(BigDecimal monthlyPayment) throws Exception {
        // Check to make sure monthlyPayment is greater than accrued interest
        if (monthlyPayment.compareTo(getInterestAccrued()) > 0) {
            this.monthlyPayment = monthlyPayment;
        } else {
            throw new Exception("Monthly payment must be more than monthly accrued interest.");
        }
    }

    protected BigDecimal getInitialPrinciple() {
        return initialPrinciple;
    }

    //Methods
    public BigDecimal getInterestRatePerMonth() {
        return interestRate.divide(new BigDecimal("12"), 10, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getInterestAccrued() {
        return currentPrinciple.multiply(getInterestRatePerMonth());
    }

    public void makePayment (BigDecimal principalPayment) {
        currentPrinciple = currentPrinciple.subtract(principalPayment);
    }

    public BigDecimal principlePayment(BigDecimal monthlyPayment) {
        /*
        If monthlyPayment is greater than or equal to currentPrinciple + accrued interest,
        Only deduct the current principle for the principlePayment.
        Otherwise, deduct the monthlyPayment - interest accrued.
         */
        if (monthlyPayment.compareTo(currentPrinciple.add(getInterestAccrued()))>=0) {
            return currentPrinciple;
        }
        return monthlyPayment.subtract(getInterestAccrued());
    }

    public String amortizationSchedule() {
        int month = 0;

        String schedule = "\n  | Beginning | Interest | Principle | Remaining\n";

        while ((currentPrinciple.setScale(4, RoundingMode.DOWN)).compareTo(BigDecimal.ZERO) >0) {
            month++;

            schedule += "\n" + month;
            schedule += " | $" + String.format("%.2f", currentPrinciple);
            schedule += " | $" + String.format("%.2f", getInterestAccrued());
            schedule += " | $" + String.format("%.2f", principlePayment(monthlyPayment));

            makePayment(principlePayment(monthlyPayment));

            schedule += " | $" + String.format("%.2f", currentPrinciple);
        }
        return schedule;
    }
}

