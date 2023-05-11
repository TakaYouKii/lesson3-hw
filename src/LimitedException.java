public class LimitedException extends Exception{
    private double remainingAmount;

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public LimitedException(String message, double remainingAmount) {
        super(message);
        this.remainingAmount = remainingAmount;
    }
}
