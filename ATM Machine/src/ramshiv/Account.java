package ramshiv;

import java.text.DecimalFormat;

 //Account: holds customer/pin and balances and performs calculations.

public class Account {
    private int customerNumber;
    private int pinNumber;
    protected double checkingBalance = 0.0;
    protected double savingBalance = 0.0;

    // For printing money values
    protected DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    // Setters / Getters
    public int setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
        return this.customerNumber;
    }

    public int getCustomerNumber() {
        return this.customerNumber;
    }

    public int setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
        return this.pinNumber;
    }

    public int getPinNumber() {
        return this.pinNumber;
    }

    // Balance getters
    public double getCheckingBalance() {
        return this.checkingBalance;
    }

    public double getSavingBalance() {
        return this.savingBalance;
    }

    // Balance operations
    public double calcCheckingWithdraw(double amount) {
        this.checkingBalance -= amount;
        return this.checkingBalance;
    }

    public double calcCheckingDeposit(double amount) {
        this.checkingBalance += amount;
        return this.checkingBalance;
    }

    public double calcSavingWithdraw(double amount) {
        this.savingBalance -= amount;
        return this.savingBalance;
    }

    public double calcSavingDeposit(double amount) {
        this.savingBalance += amount;
        return this.savingBalance;
    }
}

