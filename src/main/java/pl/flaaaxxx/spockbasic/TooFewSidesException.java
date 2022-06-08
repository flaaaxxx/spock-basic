package pl.flaaaxxx.spockbasic;

public class TooFewSidesException extends IllegalArgumentException {
    private final int numberOfSides;

    public TooFewSidesException(String message, int numberOfSides) {
        super(message);
        this.numberOfSides = numberOfSides;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }
}