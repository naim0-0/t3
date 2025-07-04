
class CurrentAccount extends Account {
    public CurrentAccount(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(" Withdrew $" + amount + " from Current Account.");
        } else {
            System.out.println(" Insufficient balance in Current Account.");
        }
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println(" Deposited $" + amount + " to Current Account.");
    }

    @Override
    public boolean canTransfer() {
        return true;
    }
}
