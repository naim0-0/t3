import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        CurrentAccount current = new CurrentAccount(2000);
        SavingsAccount savings = new SavingsAccount(1500);
        DepositPremiumAccount depositPremium = new DepositPremiumAccount();

        Account selectedAccount = null;

        while (true) {
            System.out.println("\n--- Select Account ---");
            System.out.println("1. Current Account");
            System.out.println("2. Savings Account");
            System.out.println("3. Deposit Premium Account");
            System.out.println("4. Show All Balances");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int accountChoice = scanner.nextInt();

            if (accountChoice == 0) break;

            switch (accountChoice) {
                case 1 -> selectedAccount = current;
                case 2 -> selectedAccount = savings;
                case 3 -> selectedAccount = depositPremium;
                case 4 -> {
                    printBalances(current, savings, depositPremium);
                    continue;
                }
                default -> {
                    System.out.println("Invalid choice.");
                    continue;
                }
            }

            System.out.println("\nSelected: "+selectedAccount.getClass().getSimpleName());

            while (true) {
                System.out.println("\n--- Choose Action ---");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Transfer");
                System.out.println("4. Apply Interest (if applicable)");
                System.out.println("5. Back to Account Selection");
                System.out.print("Enter action: ");
                int action=scanner.nextInt();

                if (action==5) break;

                switch(action) {
                    case 1 -> {
                        System.out.print("Enter deposit amount: ");
                        double amt = scanner.nextDouble();
                        selectedAccount.deposit(amt);
                    }
                    case 2 -> {
                        System.out.print("Enter withdraw amount: ");
                        double amt = scanner.nextDouble();
                        selectedAccount.withdraw(amt);
                    }
                    case 3 -> {
                        if (!selectedAccount.canTransfer()) {
                            System.out.println("❌ Transfers not allowed from this account.");
                            break;
                        }
                        System.out.println("Transfer to:");
                        System.out.println("1. Current Account");
                        System.out.println("2. Savings Account");
                        System.out.println("3. Deposit Premium Account");
                        System.out.print("Enter target account: ");
                        int targetChoice = scanner.nextInt();

                        Account target = switch (targetChoice) {
                            case 1 -> current;
                            case 2 -> savings;
                            case 3 -> depositPremium;
                            default -> null;
                        };

                        if (target == null || target == selectedAccount) {
                            System.out.println("❌ Invalid target.");
                            break;
                        }

                        System.out.print("Enter transfer amount: ");
                        double amt = scanner.nextDouble();
                        selectedAccount.transferTo(target, amt);
                    }
                    case 4 -> {
                        if (selectedAccount instanceof SavingsAccount sa) {
                            sa.applyInterest();
                        } else if (selectedAccount instanceof DepositPremiumAccount dpa) {
                            dpa.applyInterest();
                        } else {
                            System.out.println(" No interest for this account.");
                        }
                    }
                    default -> System.out.println(" Invalid action.");
                }
            }
        }

        System.out.println("\n👋 Exiting. Final Balances:");
        printBalances(current, savings, depositPremium);
        scanner.close();
    }

    private static void printBalances(Account... accounts) {
        System.out.println("\n📊 Account Balances:");
        for (Account acc : accounts) {
            System.out.printf("%-25s : $%.2f\n", acc.getClass().getSimpleName(), acc.getBalance());
        }
    }
}
