package org.javaacademy.taxi.park;

import lombok.Getter;
import org.javaacademy.taxi.Client;
import org.javaacademy.taxi.Time;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.Arrays;

@Getter
public class TaxiCar {
    @Value("${taxi.rate_day}")
    private BigDecimal rateDay;
    @Value("${taxi.rate_night}")
    private BigDecimal rateNight;
    private final String numberAuto;
    private BigDecimal earnings = BigDecimal.ZERO;

    public TaxiCar(String numberAuto) {
        this.numberAuto = numberAuto;
    }

    public BigDecimal acceptOrder(Client client, Time time) throws Exception {
        String exceptionString = "Ошибка расчета тарификации. Адрес отсутствует в тарифной сетке.";
        BigDecimal currentRate = (time == Time.DAY) ? rateDay : rateNight;
        int tariffDistance = Arrays.stream(Rater.values())
                .filter(e -> e.getAddress().equals(client.getAddress()))
                .findFirst()
                .orElseThrow(() -> new Exception(exceptionString))
                .getDistance();
        BigDecimal totalEarnings = currentRate.multiply(BigDecimal.valueOf(tariffDistance));
        BigDecimal currentEarnings = totalEarnings.divide(BigDecimal.valueOf(2));
        earnings = earnings.add(currentEarnings);
        return currentEarnings;
    }
}
