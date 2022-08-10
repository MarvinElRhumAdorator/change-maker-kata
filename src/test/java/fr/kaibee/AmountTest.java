package fr.kaibee;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmountTest {
    @Test
    void should_be_equal_when_same_numeric_value() {
        assertThat(Amount.of("1.5")).isEqualTo(Amount.of("1.50"));
    }
}
