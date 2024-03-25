package org.javaacademy.taxi;

import org.javaacademy.taxi.park.TaxiCar;
import org.javaacademy.taxi.park.TaxiCompany;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.*;

/**
 * 1. Создать spring boot проект, у которого есть два профиля: luna, bluz
 * 2. Есть клиент: содержит в себе адрес.
 * 3.1 Есть машина(такси): свойства ставка за км ночная (из ресурсов), ставка за км дневная (из ресурсов),
 * номер машины, сумма заработанных денег.
 * 3.2 Умеет принимать заказ: на вход клиент, день или ночь.
 * Из клиента берем адрес и сопоставляем по тарифной сетке:
 * Березовая роща - 10км
 * Кандикюля - 4км
 * Строитель - 12км
 * Остальное - ошибка
 * На основании адреса, узнаем сколько ехать, и считаем сумму заработанных денег (ставка * км).
 * 50% оставляем себе, 50% идет в таксопарк.
 *
 * 4.1 Есть таксопарк, свойства: имя (из ресурсов), сумма заработанных денег (со всех такси).
 * 4.2 Таксопарк умеет принимать заказ - на вход клиент,
 * день или ночь и передавать одной из машин заказ (по очереди).
 * Если возникает ошибка из за неизвестного адреса,
 * то этот заказ просто не выполняют (программа продолжает работу).
 * 4.3 Таксопарк умеет подводить итог:
 * "[Имя таксопарка]
 *  Заработано: [заработок таксопарка]
 *  Водитель машины[номер машины] заработал: [сумма заработка машины]
 *  Водитель машины[номер машины] заработал: [сумма заработка машины]
 *  [и тд в зависимости от количества машин]
 * "
 *
 * 5.1 В такси Luna есть 3 машины. Ставка дневная - 10 руб, ставка ночная - 15.
 * 5.2 В такси Bluz есть 2 машины. Ставка дневная - 7 руб, ставка ночная - 30.
 * 6.1 В раннере сделать 4 клиентов.
 * 	1 клиент - Кандикюля
 * 	2 клиент - Строитель
 * 	3 клиент - Березовая роща
 * 	4 клиент - Ломоносов
 * 6.2 Сделать заказы в таксопарке с этими клиентами: 1,2 клиент едут днем; 3,4 ночью.
 * 6.3 При закрытии контекста должен вызваться итог таксопарка.
 * 6.4. Проверить, что ваш итог меняется в зависимости от выбранного профиля.
* */
@SpringBootApplication
public class Runner {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);

		Client client1 = new Client("Кандикюля");
		Client client2 = new Client("Строитель");
		Client client3 = new Client("Березовая роща");
		Client client4 = new Client("Ломоносов");

		TaxiCompany company = context.getBean(TaxiCompany.class);
		Map<String, TaxiCar> taxiCars = context.getBeansOfType(TaxiCar.class);
		company.getTaxiCars().addAll(taxiCars.values());
		company.acceptOrder(client1, Time.DAY);
		company.acceptOrder(client2, Time.DAY);
		company.acceptOrder(client3, Time.NIGHT);
		company.acceptOrder(client4, Time.NIGHT);

		context.close();
	}
}
