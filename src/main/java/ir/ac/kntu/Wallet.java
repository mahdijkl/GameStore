package ir.ac.kntu;

public class Wallet {

    private double balance;

    public Wallet() {
        balance = 0;
    }

    public Double withdraw(double amount) {
        if (amount < balance) {
            balance -= amount;
            return balance;
        }
        return null;

    }

    public Double deposit(double amount) {
        balance += amount;
        System.out.print("your balance is: ");
        return balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}