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
    private String numberAuto;
    private BigDecimal earnings = BigDecimal.ZERO;

    public TaxiCar(String numberAuto) {
        this.numberAuto = numberAuto;
    }

    public BigDecimal acceptOrder(Client client, Time time) throws Exception {
        BigDecimal currentRate = (time == Time.DAY) ? rateDay : rateNight;
        int tariffDistance = Arrays.stream(TariffSchedule.values())
                .filter(e -> e.getAddress().equals(client.getAddress()))
                .findFirst()
                .orElseThrow(() -> new Exception("Ошибка расчета тарификации. Адрес отсутствует в тарифной сетке.")).getDistance();
        BigDecimal totalEarnings = currentRate.multiply(BigDecimal.valueOf(tariffDistance));
        BigDecimal currentEarnings = totalEarnings.divide(BigDecimal.valueOf(2));
        earnings = earnings.add(currentEarnings);
        return currentEarnings;
    }
}
