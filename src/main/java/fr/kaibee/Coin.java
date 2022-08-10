package fr.kaibee;

public record Coin(Amount amount) {
    public static Coin of(int valueInCents) {
        return new Coin(new Amount(valueInCents));
    }

    public static Coin of(Amount amount) {
        return new Coin(amount);
    }
}
