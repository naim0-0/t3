abstract class Account {
    protected double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public abstract void withdraw(double amount);

    public abstract void deposit(double amount);

    public abstract boolean canTransfer();

    public void transferTo(Account target, double amount) {
        if (!canTransfer()) {
            System.out.println(" Transfer not allowed from this account.");
            return;
        }

        if (this == target) {
            System.out.println(" Cannot transfer to the same account.");
            return;
        }

        double originalBalance = balance;
        this.withdraw(amount);

        // Withdraw may fail (balance unchanged), only deposit if successful
        if (balance < originalBalance) {
            target.deposit(amount);
            System.out.println(" Transferred $" + amount + " from " +
                    this.getClass().getSimpleName() + " to " + target.getClass().getSimpleName());
        }
    }

    public double getBalance() {
        return balance;
    }
}
