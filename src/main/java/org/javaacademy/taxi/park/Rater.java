package org.javaacademy.taxi.park;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rater {
    BEREZOVAYA_ROSCHA("Березовая роща", 10),
    KANDIKYULYA("Кандикюля", 4),
    STROITEL("Строитель", 12);

    private final String address;
    private final int distance;
}
