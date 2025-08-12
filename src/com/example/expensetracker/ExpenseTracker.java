package com.example.expensetracker;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {
    private final ArrayList<Expense> expenses = new ArrayList<>();

    public void addExpense(String desc, double amt) {
        expenses.add(new Expense(desc, amt));
    }

    public void listExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("\nYour Expenses:");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, expenses.get(i));
        }
    }

    public double total() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseTracker app = new ExpenseTracker();

        while (true) {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Total Spent");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String line = sc.nextLine();
            int choice;
            try { choice = Integer.parseInt(line.trim()); } catch (Exception e) { choice = -1; }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter amount: ");
                    try {
                        double amt = Double.parseDouble(sc.nextLine());
                        app.addExpense(desc, amt);
                        System.out.println("Expense added!");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount — please use a number.");
                    }
                }
                case 2 -> app.listExpenses();
                case 3 -> System.out.printf("Total Spent: $%.2f%n", app.total());
                case 4 -> {
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid option, please choose again.");
            }
        }
    }
}
