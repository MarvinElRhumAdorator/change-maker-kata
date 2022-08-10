package fr.kaibee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class VendingMachine {

    private final List<Coin> availableCoins;

    public VendingMachine(Set<Coin> coinDenominations) {
        availableCoins = coinDenominations.stream()
                .sorted(Comparator.comparing(Coin::amount).reversed())
                .toList();
    }

    public Coin[] calculateChange(Amount purchaseAmount, Amount tenderAmount) {
        Amount remainingChangeAmount = tenderAmount.remove(purchaseAmount);

        List<Coin> changeCoins = new ArrayList<>();
        for (Coin currentCoin : availableCoins) {
            while (remainingChangeAmount.canRemove(currentCoin.amount())) {
                changeCoins.add(currentCoin);
                remainingChangeAmount = remainingChangeAmount.remove(currentCoin.amount());
            }
        }

        return changeCoins.toArray(Coin[]::new);
    }

}
