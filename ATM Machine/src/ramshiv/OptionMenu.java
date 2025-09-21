package ramshiv;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * OptionMenu: handles login and user menus. Extends Account to use balances and calculations.
 */
public class OptionMenu extends Account {
    private Scanner menuInput = new Scanner(System.in);
    private HashMap<Integer, Integer> data = new HashMap<>();
    private int selection;

    public OptionMenu() {
        // sample customers (in real app don't hardcode credentials)
        data.put(123456789, 1234);
        data.put(987654321, 9876);
    }

    // Validate Login information: customer number and pin number
    public void getLogin() throws IOException {
        boolean authenticated = false;

        System.out.println("Welcome to the ATM Project!");

        do {
            try {
                System.out.print("Enter Your Customer Number: ");
                int cust = Integer.parseInt(menuInput.nextLine().trim());
                setCustomerNumber(cust);

                System.out.print("Enter Your Pin Number: ");
                int pin = Integer.parseInt(menuInput.nextLine().trim());
                setPinNumber(pin);

                if (data.containsKey(getCustomerNumber()) && data.get(getCustomerNumber()) == getPinNumber()) {
                    authenticated = true;
                    getAccountType();
                } else {
                    System.out.println("\nWrong Customer Number or Pin Number.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid character(s). Only numbers allowed.\n");
            }
        } while (!authenticated);
    }

    // Display Account Type Menu with selection
    public void getAccountType() {
        System.out.println("\nSelect the Account you want to access: ");
        System.out.println("Type 1 - Checking Account");
        System.out.println("Type 2 - Saving Account");
        System.out.println("Type 3 - Exit");
        System.out.print("Choice: ");

        try {
            selection = Integer.parseInt(menuInput.nextLine().trim());
        } catch (NumberFormatException e) {
            selection = -1;
        }

        switch (selection) {
            case 1:
                getChecking();
                break;

            case 2:
                getSaving();
                break;

            case 3:
                System.out.println("Thank You for using this ATM.");
                break;

            default:
                System.out.println("\nInvalid Choice.\n");
                getAccountType();
        }
    }

    // Checking Account Menu
    public void getChecking() {
        System.out.println("\nChecking Account: ");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Funds");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
        System.out.print("Choice: ");

        try {
            selection = Integer.parseInt(menuInput.nextLine().trim());
        } catch (NumberFormatException e) {
            selection = -1;
        }

        switch (selection) {
            case 1:
                System.out.println("Checking Account Balance: " + moneyFormat.format(getCheckingBalance()));
                getAccountType();
                break;

            case 2:
                getCheckingWithdrawInput();
                getAccountType();
                break;

            case 3:
                getCheckingDepositInput();
                getAccountType();
                break;

            case 4:
                System.out.println("Thank You for using this ATM.");
                break;

            default:
                System.out.println("\nInvalid choice.\n");
                getChecking();
        }
    }

    // Saving Account Menu
    public void getSaving() {
        System.out.println("\nSaving Account: ");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Funds");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
        System.out.print("Choice: ");

        try {
            selection = Integer.parseInt(menuInput.nextLine().trim());
        } catch (NumberFormatException e) {
            selection = -1;
        }

        switch (selection) {
            case 1:
                System.out.println("Saving Account Balance: " + moneyFormat.format(getSavingBalance()));
                getAccountType();
                break;

            case 2:
                getSavingWithdrawInput();
                getAccountType();
                break;

            case 3:
                getSavingDepositInput();
                getAccountType();
                break;

            case 4:
                System.out.println("Thank You for using this ATM.");
                break;

            default:
                System.out.println("\nInvalid choice.\n");
                getSaving();
        }
    }

    // Checking withdraw input
    public void getCheckingWithdrawInput() {
        System.out.println("Checking Account Balance: " + moneyFormat.format(getCheckingBalance()));
        System.out.print("Amount you want to withdraw from Checking Account: ");

        try {
            double amount = Double.parseDouble(menuInput.nextLine().trim());
            if (amount < 0) {
                System.out.println("Amount must be positive.\n");
                return;
            }
            if ((getCheckingBalance() - amount) >= 0) {
                calcCheckingWithdraw(amount);
                System.out.println("New Checking Account balance: " + moneyFormat.format(getCheckingBalance()));
            } else {
                System.out.println("Balance cannot be negative.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.\n");
        }
    }

    // Checking deposit input
    public void getCheckingDepositInput() {
        System.out.println("Checking Account Balance: " + moneyFormat.format(getCheckingBalance()));
        System.out.print("Amount you want to deposit to Checking Account: ");

        try {
            double amount = Double.parseDouble(menuInput.nextLine().trim());
            if (amount < 0) {
                System.out.println("Amount must be positive.\n");
                return;
            }
            calcCheckingDeposit(amount);
            System.out.println("New Checking Account balance: " + moneyFormat.format(getCheckingBalance()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.\n");
        }
    }

    // Saving withdraw input
    public void getSavingWithdrawInput() {
        System.out.println("Saving Account Balance: " + moneyFormat.format(getSavingBalance()));
        System.out.print("Amount you want to withdraw from Saving Account: ");

        try {
            double amount = Double.parseDouble(menuInput.nextLine().trim());
            if (amount < 0) {
                System.out.println("Amount must be positive.\n");
                return;
            }
            if ((getSavingBalance() - amount) >= 0) {
                calcSavingWithdraw(amount);
                System.out.println("New Saving Account balance: " + moneyFormat.format(getSavingBalance()));
            } else {
                System.out.println("Balance cannot be negative.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.\n");
        }
    }

    // Saving deposit input
    public void getSavingDepositInput() {
        System.out.println("Saving Account Balance: " + moneyFormat.format(getSavingBalance()));
        System.out.print("Amount you want to deposit to Saving Account: ");

        try {
            double amount = Double.parseDouble(menuInput.nextLine().trim());
            if (amount < 0) {
                System.out.println("Amount must be positive.\n");
                return;
            }
            calcSavingDeposit(amount);
            System.out.println("New Saving Account balance: " + moneyFormat.format(getSavingBalance()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.\n");
        }
    }
}
