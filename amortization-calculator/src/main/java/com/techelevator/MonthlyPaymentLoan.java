package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MonthlyPaymentLoan extends LoanCalculator {
    //Instance Variables
    private final BigDecimal monthlyPayment;

    //Constructors
    /**
     *
     * @param principle The principle loan amount
     * @param interestRate APR as a whole number or decimal
     * @param monthlyPayment Monthly payments
     */
    public MonthlyPaymentLoan(BigDecimal principle, BigDecimal interestRate, BigDecimal monthlyPayment){
        super(principle,interestRate);
        this.monthlyPayment = monthlyPayment;
    }

    //Methods
    public String amortizationSchedule() {
        String header = "\nAmortization Schedule Given The Monthly Payments\n";
        return super.amortizationSchedule(monthlyPayment, header);
    }

}
