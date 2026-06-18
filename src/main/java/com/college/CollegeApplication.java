package com.college;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Веб-додаток для перегляду та редагування розкладу коледжу.
 *
 * @SpringBootApplication - анотація для позначення головного класу Spring Boot додатку.
 *
 * Методи:
 * - main(String[] args): запускає додаток Spring Boot з вбудованим веб-сервером.
 * - viewAllSchedules(): виводить всі розклади з бази даних.
 * - dropAllSchedules(): видаляє всі розклади з бази даних.
 *
 * Поля:
 * - scheduleRepository: репозиторій для роботи з розкладами.
 *
 * Використовує:
 * - Schedule для представлення документу розкладу.
 * - ScheduleRepository для взаємодії з базою даних.
 */
@SpringBootApplication
public class CollegeApplication {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public static void main(String[] args) {
        SpringApplication.run(CollegeApplication.class, args);
    }

    private void viewAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        if (schedules.isEmpty()) {
            System.out.println("Документи з рядками розкладу не знайдено.");
        } else {
            System.out.println("Знайдено " + schedules.size() + " документів розкладу:");
            schedules.forEach(System.out::println);
        }
    }

    private void dropAllSchedules() {
        scheduleRepository.deleteAll();
        System.out.println("Розклад видалено.");
    }
}
