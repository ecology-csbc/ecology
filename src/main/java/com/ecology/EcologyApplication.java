package com.ecology;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Веб-додаток для управління екологічними записами.
 *
 * @SpringBootApplication - анотація для позначення головного класу Spring Boot додатку.
 *
 * Методи:
 * - main(String[] args): запускає додаток Spring Boot з вбудованим веб-сервером.
 * - viewAllEcologies(): виводить всі записи з бази даних.
 * - dropAllEcologies(): видаляє всі записи з бази даних.
 *
 * Поля:
 * - ecologyRepository: репозиторій для роботи з записами.
 *
 * Використовує:
 * - Ecology для представлення документу запису.
 * - EcologyRepository для взаємодії з базою даних.
 */
@SpringBootApplication
public class EcologyApplication {

    @Autowired
    private EcologyRepository ecologyRepository;

    public static void main(String[] args) {
        SpringApplication.run(EcologyApplication.class, args);
    }

    private void viewAllEcologies() {
        List<Ecology> ecologies = ecologyRepository.findAll();
        if (ecologies.isEmpty()) {
            System.out.println("Документи екологічних записів не знайдено.");
        } else {
            System.out.println("Знайдено " + ecologies.size() + " документів записів:");
            ecologies.forEach(System.out::println);
        }
    }

    private void dropAllEcologies() {
        ecologyRepository.deleteAll();
        System.out.println("Всі записи видалено.");
    }
}
