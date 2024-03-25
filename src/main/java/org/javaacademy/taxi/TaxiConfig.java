package org.javaacademy.taxi;

import org.javaacademy.taxi.park.TaxiCar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class TaxiConfig {

    @Bean
    @Profile("bluz")
    public TaxiCar taxiCarBluz1() {
        return new TaxiCar("b001bb");
    }

    @Bean
    @Profile("bluz")
    public TaxiCar taxiCarBluz2() {
        return new TaxiCar("b002bb");
    }

    @Bean
    @Profile("luna")
    public TaxiCar taxiCarLuna1() {
        return new TaxiCar("l001ll");
    }

    @Bean
    @Profile("luna")
    public TaxiCar taxiCarLuna2() {
        return new TaxiCar("l002ll");
    }

    @Bean
    @Profile("luna")
    public TaxiCar taxiCarLuna3() {
        return new TaxiCar("l003ll");
    }
}
