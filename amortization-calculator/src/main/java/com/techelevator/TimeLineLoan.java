package com.techelevator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TimeLineLoan extends LoanCalculator {
    private final int loanTerm;

    /**
     *
     * @param initialPrinciple The principle loan amount
     * @param interestRate APR as a whole number or decimal
     * @param loanTerm Length of loan in months
     */
    public TimeLineLoan(BigDecimal initialPrinciple, BigDecimal interestRate, int loanTerm) {
        super(initialPrinciple, interestRate);
        this.loanTerm = loanTerm;
    }

    /*   TODO This formula works for monthly payments with monthly compounds
       P = loan amount
        i = interest rate per period
           r = apr
           m = compounds per year (annually - 1, semi-annually - 1, monthly - 12)
           i = r/m
       n = equal periodic payments
           m = payments per year (annually - 1, semi-annually - 1, monthly - 12)
           t = number of years
           n= m*t
       R = Payment at the end of each period

       R = (P * i) / {1 - [(1 + i) ^ -n]}

       Monthly Payments = (initialPrinciple * interestRatePerMonth) / (1 - ((1 + interestRatePerMonth) ^ -(loanTerm)))
        */ //TODO I don't think it works with monthly payments and daily compounds
    //Methods
    private BigDecimal numeratorFormula() {
        //numerator = (initialPrinciple * interestRatePerMonth)
        BigDecimal numerator;
        numerator = getInitialPrinciple().multiply(interestRatePerMonth());
        return numerator;
    }
    private BigDecimal denominatorFormula() {
        // denominator = (1 - ((1 + interestRatePerMonth) ^ -(loanTerm)))
        BigDecimal denominator;
        // interestRatePerMonth + 1
        denominator = interestRatePerMonth().add(BigDecimal.ONE);
        // raise to the power of loanTerm
        denominator = denominator.pow(loanTerm).setScale(32, RoundingMode.HALF_EVEN);
        // 1 / denominator is the same a raising denominator to a negative power.
        denominator = BigDecimal.ONE.divide(denominator, 32 , RoundingMode.HALF_EVEN);
        // subtract result from one
        denominator = BigDecimal.ONE.subtract(denominator);
        return denominator;

    }
    public BigDecimal monthlyPayments() {
        BigDecimal monthlyPayment;
        monthlyPayment = numeratorFormula().divide(denominatorFormula(), 4, RoundingMode.HALF_EVEN);
        return monthlyPayment;
    }
    public String amortizationSchedule() {
        String header = "\nAmortization Schedule Given A Set Time Line For The Loan\n";
        return super.amortizationSchedule(monthlyPayments(), header);
    }



}
