package classes;

public class SimCard {

    private final String phoneNumber;
    private boolean isActive;
    private double creditBalance;

    public SimCard(String phoneNumber,boolean isActive, double creditBalance) {
        this.phoneNumber = phoneNumber;
        this.creditBalance = creditBalance;
        this.isActive = isActive;
    }

    //=================================================================

    public String getPhoneNumber() { return phoneNumber; }
    public boolean isActive() { return isActive; }
    public double getCreditBalance() { return creditBalance; }

    public void addCredit(double amount) {
        if (amount > 0) {
            creditBalance += amount;
        }
    }

    public void deductCredit(double amount) {
        if (amount > 0 && creditBalance >= amount) {
            creditBalance -= amount;
        }
    }

    public boolean isEnoughCredit(double amount) {
        return creditBalance >= amount;
    }
}