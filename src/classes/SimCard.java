package classes;

public class SimCard {

    //==================== Attributes ===================================

    private final String phoneNumber; // Phone Number is Unique => final
    private boolean isActive; // Active Status
    private double creditBalance;

    
    //=================== Constructor ===================================

    public SimCard(String phoneNumber,boolean isActive, double creditBalance) {
        this.phoneNumber = phoneNumber;
        this.creditBalance = creditBalance;
        this.isActive = isActive;
    }

    //============== Getters & Setters ================================
    
    public String getPhoneNumber() { return this.phoneNumber; }
    public boolean isActive() { return this.isActive; }
    public double getCreditBalance() { return this.creditBalance; }
    public void setActiveStatus(boolean status) { this.isActive = status; }
    
    //======================== Methods ================================

    public void addCredit(double amount) {
        if (amount > 0) {
            this.creditBalance += amount;
        }
    }

    public void deductCredit(double amount) {
        if (amount > 0 && this.creditBalance >= amount) {
            this.creditBalance -= amount;
        }
    }

    public boolean isEnoughCredit(double amount) {
        return this.creditBalance >= amount;
    }
}
