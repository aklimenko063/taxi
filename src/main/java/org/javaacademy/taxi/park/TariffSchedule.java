package org.javaacademy.taxi.park;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum TariffSchedule {
    BEREZOVAYA_ROSCHA("Березовая роща", 10),
    KANDIKYULYA("Кандикюля", 4),
    STROITEL("Строитель", 12);

    private String address;
    private int distance;
}
