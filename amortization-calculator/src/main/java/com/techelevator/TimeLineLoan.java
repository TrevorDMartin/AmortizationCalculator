package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TimeLineLoan extends LoanCalculator {
    private final int loanTerm;
    private BigDecimal numerator;
    private BigDecimal denominator;
    private BigDecimal monthlyPayment;

    /**
     *
     * @param initialPrinciple The principle loan amount
     * @param interestRate APR as a whole number or decimal
     * @param loanTerm Length of loan in months
     */
    public TimeLineLoan(BigDecimal initialPrinciple, BigDecimal interestRate, int loanTerm) throws Exception {

        super(initialPrinciple, interestRate);

        if (loanTerm <= 0) {
            throw new Exception("Loan term value must be greater than zero.");
        } else {
            this.loanTerm = loanTerm;
        }

        monthlyPayments();
        super.setMonthlyPayment(monthlyPayment);
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
    public void monthlyPayments() {

        numeratorFormula();
        denominatorFormula();

        monthlyPayment = numerator.divide(denominator, 4, RoundingMode.HALF_EVEN);
    }
    private void numeratorFormula() {
        /*
        NUMERATOR FORMULA
        (P * i)
        (initialPrinciple * interestRatePerMonth)
         */

        numerator = super.getInitialPrinciple().multiply(super.getInterestRatePerMonth());
    }
    private void denominatorFormula() {
        /*
        DENOMINATOR FORMULA
        {1 - [(1 + i) ^ -n]}
        (1 - ((1 + interestRatePerMonth) ^ -(loanTerm)))
        (1 - (1 / ((1 + interestRatePerMonth) ^ loanTerm)))

        ORDER OF OPERATIONS
        1 + interestRatePerMonth
        this ^ loanTerm
        1 / this
        1 - this
         */

        denominator = super.getInterestRatePerMonth().add(BigDecimal.ONE);
        denominator = denominator.pow(loanTerm).setScale(32, RoundingMode.HALF_EVEN);
        denominator = BigDecimal.ONE.divide(denominator, 32 , RoundingMode.HALF_EVEN);
        denominator = BigDecimal.ONE.subtract(denominator);
    }



}
