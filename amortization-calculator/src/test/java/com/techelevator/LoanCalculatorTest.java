package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class LoanCalculatorTest {
    LoanCalculator loan;

//    @Test
//    public void test_set_and_constructor_interest_rate_using_decimal_values() {
//        loan = new LoanCalculator(BigDecimal.TEN, BigDecimal.valueOf(0.05));
//        Assert.assertEquals("Initialized with constructor, without monthlyPayment, using decimal interest",0, loan.getInterestRate().compareTo(BigDecimal.valueOf(.05)));
//        loan = new LoanCalculator(BigDecimal.TEN, BigDecimal.valueOf(.99), BigDecimal.valueOf(1));
//        Assert.assertEquals("Initialized with constructor, with monthlyPayment, using decimal interest", 0, loan.getInterestRate().compareTo(BigDecimal.valueOf(.99)));
//        loan = new LoanCalculator(BigDecimal.TEN, BigDecimal.valueOf(.05));
//        Assert.assertEquals("Initialized with constructor, without monthlyPayment, using decimal interest", 0, loan.getInterestRate().compareTo(BigDecimal.valueOf(.05)));
//        loan = new LoanCalculator(BigDecimal.TEN, BigDecimal.valueOf(.99), BigDecimal.ONE);
//        Assert.assertEquals("Initialized with constructor, with monthlyPayment, using decimal interest", 0, loan.getInterestRate().compareTo(BigDecimal.valueOf(.99)));
//    }
//
//    @Test
//    public void test_set_and_constructor_interest_rate_using_whole_values() {
//        loan = new LoanCalculator(BigDecimal.TEN, BigDecimal.valueOf(5));
//        Assert.assertEquals("Initialized with constructor, without monthlyPayment, using whole number interest",0, loan.getInterestRate().compareTo(BigDecimal.valueOf(.05)));
//        loan = new LoanCalculator(BigDecimal.TEN, BigDecimal.ONE, BigDecimal.ONE);
//        Assert.assertEquals("Initialized with constructor, with monthlyPayment, using whole number interest", 0, loan.getInterestRate().compareTo(BigDecimal.valueOf(.01)));
//        loan = new LoanCalculator(BigDecimal.TEN, BigDecimal.valueOf(99));
//        Assert.assertEquals("Initialized with constructor, without monthlyPayment, using whole number interest", 0, loan.getInterestRate().compareTo(BigDecimal.valueOf(.99)));
//        loan = new LoanCalculator(BigDecimal.TEN, BigDecimal.valueOf(5), BigDecimal.ONE);
//        Assert.assertEquals("Initialized with constructor, with monthlyPayment, using whole number interest", 0, loan.getInterestRate().compareTo(BigDecimal.valueOf(.05)));
//    }
//
//    @Test
//    public void test_get_interest_and_get_interest_whole_number() {
//        loan = new LoanCalculator(BigDecimal.TEN, BigDecimal.valueOf(5));
//        Assert.assertEquals(0, loan.getInterestRate().compareTo(BigDecimal.valueOf(.05)));
//        Assert.assertEquals(0, loan.getInterestRateWholeNumber().compareTo(BigDecimal.valueOf(5)));
//
//        loan = new LoanCalculator(BigDecimal.TEN, BigDecimal.valueOf(.09));
//        Assert.assertEquals(0, loan.getInterestRate().compareTo(BigDecimal.valueOf(.09)));
//        Assert.assertEquals(0, loan.getInterestRateWholeNumber().compareTo(BigDecimal.valueOf(9)));
//    }
//
//    @Test
//    public void test_compound_per_month() {
//        loan = new LoanCalculator(BigDecimal.TEN, BigDecimal.valueOf(12));
//        loan.compoundPerMonth();
//        Assert.assertEquals(0, loan.getCurrentPrinciple().compareTo(BigDecimal.valueOf(10.1)));
//
//
//        loan = new LoanCalculator(BigDecimal.valueOf(1250), BigDecimal.valueOf(9.45));
//        loan.compoundPerMonth();
//        Assert.assertEquals(0, loan.getCurrentPrinciple().compareTo(BigDecimal.valueOf(1259.84375)));
//
//
//        loan = new LoanCalculator(BigDecimal.valueOf(7921), BigDecimal.valueOf(6.2152));
//        loan.compoundPerMonth();
//        Assert.assertEquals(0, loan.getCurrentPrinciple().compareTo(BigDecimal.valueOf(7962.02547293)));
//
//    }
}
