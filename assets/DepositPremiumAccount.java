
class DepositPremiumAccount extends Account {
    private static final double INTEREST_RATE = 0.07;
    private static final int REQUIRED_INSTALLMENTS = 5;
    private int installmentsPaid = 0;

    public DepositPremiumAccount() {
        super(0.0);
    }

    @Override
    public void withdraw(double amount) {
        if (installmentsPaid < REQUIRED_INSTALLMENTS) {
            System.out.println(" Withdrawal blocked. Complete 5 installments first.");
        } else if (amount <= balance) {
            balance -= amount;
            System.out.println(" Withdrew $" + amount + " from Deposit Premium Account.");
        } else {
            System.out.println(" Insufficient balance.");
        }
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        installmentsPaid++;
        System.out.println(" Installment " + installmentsPaid + "/5: Deposited $" + amount);
    }

    public void applyInterest() {
        if (installmentsPaid >= REQUIRED_INSTALLMENTS) {
            double interest = balance * INTEREST_RATE;
            balance += interest;
            System.out.println(" Applied 7% interest: $" + interest);
        } else {
            System.out.println(" Interest not applied. Installments incomplete.");
        }
    }

    @Override
    public boolean canTransfer() {
        return false;
    }
}
