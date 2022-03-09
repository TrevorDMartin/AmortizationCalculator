package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

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

public class MainCLI {
	// Menu Options
	private static final int MENU_LOAN_MONTHLY_PAYMENT = 1;
	private static final int MENU_LOAN_TIME_PERIOD = 2;
	private static final int MENU_EXIT = 3;

	private static BigDecimal principle;
	private static BigDecimal interestRate;

	private static LoanCalculator loan;

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		/*
		I tried to write this program using OOP.
		The MainCLI class deals with all input and output.
		I set up the classes to throw exceptions containing useful messages
		that the view controller can then output to the user.
		 */

		while (true) {
			try {
				int choice = getUserChoice();

				if (choice == MENU_EXIT) {
					System.exit(0);
					break;
				}

				principle = getPrinciple();
				interestRate = getInterestRate();

				if (choice==MENU_LOAN_MONTHLY_PAYMENT){
					loanWithMonthlyPayment(principle, interestRate);
				}

				if (choice==MENU_LOAN_TIME_PERIOD){
					loanWithTimePeriod(principle, interestRate);
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// TODO DEBUG FOR MONTHLY PAYMENTS THAT ARE LESS THAN INTEREST ACCRUED

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
		BigDecimal principle;
		System.out.println("\nPlease enter the starting principle.");
		String userInput = scanner.nextLine();

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
		BigDecimal interestRate;
		System.out.println("\nPlease enter the yearly interest rate");
		String userInput = scanner.nextLine();

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

		loan = new LoanCalculator(principle, interestRate, monthlyPayment);
		System.out.println("\nAmortization Schedule Given The Monthly Payments\n" + loan.amortizationSchedule());
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


		loan = new TimeLineLoan(principle, interestRate, loanTerm);
		System.out.println("\nAmortization Schedule Given A Set Time Line For The Loan\n" + loan.amortizationSchedule());
	}


}
