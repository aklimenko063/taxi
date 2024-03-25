package org.javaacademy.taxi.park;

import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.javaacademy.taxi.Client;
import org.javaacademy.taxi.Time;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class TaxiCompany {
    @Value("${taxi.name}")
    private String name;
    @Getter
    private final Queue<TaxiCar> taxiCars = new LinkedList<>();
    private BigDecimal earnings = BigDecimal.ZERO;

    @PreDestroy
    public void destroy() {
        infoCompany();
    }

    public void acceptOrder(Client client, Time time) {
        if (taxiCars.isEmpty()) {
            System.out.println("В таксопарке нет машин.");
        } else {
            try {
                TaxiCar taxiCar = taxiCars.peek();
                BigDecimal currentEarnings = taxiCar.acceptOrder(client, time);
                changeQueueTaxiCar();
                earnings = earnings.add(currentEarnings);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void infoCompany() {
        System.out.printf
                ("""
                Отчет таксопарка: %s
                Заработано: %s
                """, this.name, this.earnings.setScale(2, RoundingMode.HALF_UP));
        taxiCars.forEach(taxiCar -> System.out.printf("Водитель авто %s заработал: %s\n", taxiCar.getNumberAuto(),
                taxiCar.getEarnings().setScale(2, RoundingMode.HALF_UP)));
    }

    private void changeQueueTaxiCar() {
        taxiCars.add(taxiCars.poll());
    }
}
