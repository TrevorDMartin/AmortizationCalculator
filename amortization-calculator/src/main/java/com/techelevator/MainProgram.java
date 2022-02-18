package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class MainProgram {
	private static final Scanner scanner = new Scanner(System.in);

	 /*
		to calculate apy from apr
		apr / compounds per year = periodic rate //calculate as a decimal
		i = periodic interest rate
		n = periods per year
		APY(wholeNumber) = ((1 + i) ^ n) - 1

		Example:

		apr = 12%
		r = 0.01 // 12% or .12 / 12(monthsPerYear) = 0.01
		n = 12 (payments)
		((1 + .01) ^ 12) - 1 = 12.68%
		APY = 12.68%
		 */ //TODO Formula to calculate APY if needed

	public static void main(String[] args) {
		int choice;
		BigDecimal principle;
		BigDecimal interestRate;
		while (true) {
			try {
				choice = getUserChoice();
				if (choice == 3) {
					System.exit(0);
				}
				principle = getPrinciple();
				interestRate = getInterestRate();

				if (choice==1){
					loanWithMonthlyPayment(principle, interestRate);
				}
				if (choice==2){
					loanWithTimePeriod(principle, interestRate);
				}

				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// TODO DEBUG FOR MONTHLY PAYMENTS THAT ARE LESS THAN INTEREST ACCRUED
	// TODO FIGURE OUT HOW TO COMPOUND DAILY

	private static int getUserChoice() throws Exception {
		System.out.println("\nWould you like to amortize a loan with monthly payment or time period\n");
		System.out.println("1) Monthly Payment");
		System.out.println("2) Time Period");
		System.out.println("3) EXIT\n");

		String userInput = scanner.nextLine();
		int choice = Integer.parseInt(userInput);

		if (choice!=1 && choice!=2 && choice!=3) {
			throw new Exception("Input is not valid, must input 1 or 2");
		}

		return choice;
	}
	private static BigDecimal getPrinciple() throws Exception {
		System.out.println("\nPlease enter the starting principle.");
		String userInput = scanner.nextLine();
		BigDecimal principle;
		try {
			principle = new BigDecimal(userInput);
		} catch (Exception e) {
			throw new Exception("Must input a valid number");
		}

		if (principle.compareTo(BigDecimal.ZERO) <= 0) {
			throw new Exception("Must input a number greater than 0.");
		}

		return principle;
	}
	private static BigDecimal getInterestRate() throws Exception {
		System.out.println("\nPlease enter the yearly interest rate");
		String userInput = scanner.nextLine();
		BigDecimal interestRate;
		try {
			interestRate = new BigDecimal(userInput);
		} catch (Exception e) {
			throw new Exception("Must input a valid number");
		}

		if (interestRate.compareTo(BigDecimal.ZERO) <= 0) {
			throw new Exception("Must input a number greater than 0.");
		}

		return interestRate;
	}

	private static void loanWithMonthlyPayment(BigDecimal principle, BigDecimal interestRate) throws Exception {

		System.out.println("\nPlease enter the monthly payment");
		String userInput = scanner.nextLine();
		BigDecimal monthlyPayment;

		try {
			monthlyPayment = new BigDecimal(userInput);
		} catch (Exception e) {
			throw new Exception("Must input a valid number");
		}
		if (monthlyPayment.compareTo(BigDecimal.ZERO) <= 0) {
			throw new Exception("Must input a number greater than 0.");
		}

		MonthlyPaymentLoan loan = new MonthlyPaymentLoan(principle, interestRate, monthlyPayment);
		System.out.println(loan.amortizationSchedule());
	}
	private static void loanWithTimePeriod(BigDecimal principle, BigDecimal interestRate) throws Exception {

		System.out.println("\nPlease enter the loan term in months");
		String userInput = scanner.nextLine();
		int loanTerm;

		try {
			loanTerm = Integer.parseInt(userInput);
		} catch (Exception e) {
			throw new Exception("Must input a valid number");
		}
		if (loanTerm <= 0) {
			throw new Exception("Must input a number greater than 0.");
		}

		TimeLineLoan loan = new TimeLineLoan(principle, interestRate, loanTerm);
		System.out.println(loan.amortizationSchedule());
	}


}
