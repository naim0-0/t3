
class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 1000.0;
    private static final double INTEREST_RATE = 0.025;

    public SavingsAccount(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
            System.out.println(" Withdrew $" + amount + " from Savings Account.");
        } else {
            System.out.println(" Cannot withdraw. Must maintain $1000 minimum.");
        }
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println(" Deposited $" + amount + " to Savings Account.");
    }

    public void applyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println(" Applied 2.5% interest: $" + interest);
    }

    @Override
    public boolean canTransfer() {
        return true;
    }
}
