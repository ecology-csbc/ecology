package com.ecology;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Консольний додаток для управління екологічними записами.
 *
 * @SpringBootApplication - анотація для позначення головного класу Spring Boot додатку.
 *
 * Клас реалізує CommandLineRunner для виконання коду після запуску додатку.
 */
@SpringBootApplication
public class EcologyApplication implements CommandLineRunner {

    @Autowired
    private EcologyRepository ecologyRepository;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(EcologyApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Override
    public void run(String... args) {
        runLoop(new Scanner(System.in));
    }

    void runLoop(Scanner scanner) {
        while (true) {
            System.out.println("1. Завантажити екологічні записи з CSV");
            System.out.println("2. Переглянути екологічні записи");
            System.out.println("3. Видалити всі екологічні записи");
            System.out.println("4. Вихід");
            System.out.print("Введіть номер команди (1-4): ");

            if (!scanner.hasNextInt()) {
                String invalidInput = scanner.nextLine();
                System.out.println("Некоректне введення \"" + invalidInput + "\". Введіть число від 1 до 4.");
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (!handleChoice(choice)) {
                scanner.close();
                break;
            }
        }
    }

    boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                addEcologiesFromCsv();
                return true;
            case 2:
                viewAllEcologies();
                return true;
            case 3:
                dropAllEcologies();
                return true;
            case 4:
                return false;
            default:
                System.out.println("Номер команди некоректний. Спробуйте ще.");
                return true;
        }
    }

    private void addEcologiesFromCsv() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Ecology.csv");
            if (inputStream == null) {
                System.out.println("Файл Ecology.csv не знайдено в ресурсах.");
                return;
            }

            try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                List<String[]> records = reader.readAll();
                if (!records.isEmpty()) {
                    records.remove(0);
                }

                List<Ecology> ecologyList = new ArrayList<>();
                for (String[] record : records) {
                    if (record.length < 11) {
                        continue;
                    }
                    Ecology ecology = new Ecology(
                        record[0],
                        record[1],
                        record[2],
                        record[3],
                        record[4],
                        record[5],
                        record[6],
                        record[7],
                        record[8],
                        record[9],
                        record[10]
                    );
                    ecologyList.add(ecology);
                }

                ecologyRepository.deleteAll();
                ecologyRepository.saveAll(ecologyList);
                System.out.println(ecologyList.size() + " екологічних записів завантажено з CSV.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не вдалося завантажити екологічні записи з CSV.");
        }
    }

    private void viewAllEcologies() {
        List<Ecology> ecologies = ecologyRepository.findAll();
        if (ecologies.isEmpty()) {
            System.out.println("Екологічні записи не знайдено.");
        } else {
            System.out.println("Знайдено " + ecologies.size() + " екологічних записів:");
            ecologies.forEach(System.out::println);
        }
    }

    private void dropAllEcologies() {
        ecologyRepository.deleteAll();
        System.out.println("Всі екологічні записи видалено.");
    }
}
