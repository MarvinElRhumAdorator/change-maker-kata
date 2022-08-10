package fr.kaibee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class VendingMachineTest {

    @ParameterizedTest
    @MethodSource("dataProvider")
    void should_return_coins_for_change(Amount purchaseAmount, Amount tenderAmount, Coin[] expectedCoins) {
        // Given
        Set<Coin> coinDenominations = Set.of(Coin.of(1), Coin.of(5), Coin.of(10), Coin.of(25));
        VendingMachine vendingMachine = new VendingMachine(coinDenominations);

        // When
        Coin[] change = vendingMachine.calculateChange(purchaseAmount, tenderAmount);

        // Then
        assertThat(change).containsExactly(expectedCoins);
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("1.75", "2", getCoins(25)),
                Arguments.of("1.50", "2", getCoins(25, 25)),
                Arguments.of("2", "2.50", getCoins(25, 25)),
                Arguments.of("2.25", "3", getCoins(25, 25, 25)),
                Arguments.of("2", "3", getCoins(25, 25, 25, 25)),
                Arguments.of("2.40", "3", getCoins(25, 25, 10)),
                Arguments.of("2.30", "3", getCoins(25, 25, 10, 10)),
                Arguments.of("2.35", "3", getCoins(25, 25, 10, 5)),
                Arguments.of("2.34", "3", getCoins(25, 25, 10, 5, 1)),
                Arguments.of("2.33", "3", getCoins(25, 25, 10, 5, 1, 1))
        );
    }

    private static Coin[] getCoins(int... coins) {
        return Arrays.stream(coins).mapToObj(Coin::of).toArray(Coin[]::new);
    }

}
