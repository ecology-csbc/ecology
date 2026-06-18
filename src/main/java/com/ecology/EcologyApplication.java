package com.retail;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.charset.StandardCharsets;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Додаток для керування базою даних замовлень Retail.
 * 
 * @SpringBootApplication - анотація для позначення головного класу Spring Boot додатку.
 * 
 * Клас реалізує CommandLineRunner для виконання коду після запуску додатку.
 * 
 * Методи:
 * - main(String[] args): запускає додаток Spring Boot.
 * - run(String... args): метод, що виконується після запуску додатку. Виводить меню для користувача.
 * - addOrdersFromCsv(): додає замовлення з CSV-файлу до бази даних.
 * - viewAllOrders(): виводить всі замовлення з бази даних.
 * - dropAllOrders(): видаляє всі замовлення з бази даних.
 * 
 * Поля:
 * - retailOrderRepository: репозиторій для роботи з замовленнями.
 * 
 * Використовує:
 * - Scanner для зчитування вводу користувача.
 * - CSVReader для зчитування даних з CSV-файлу.
 * - RetailOrder для представлення документу замовлення.
 * - RetailOrderRepository для взаємодії з базою даних.
 */
@SpringBootApplication
public class RetailApplication implements CommandLineRunner {

    @Autowired
    private RetailOrderRepository retailOrderRepository;

    public static void main(String[] args) {
        SpringApplication.run(RetailApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Додати замовлення з CSV-файлу");
            System.out.println("2. Подивитись замовлення");
            System.out.println("3. Видалити замовлення");
            System.out.println("4. Вихід");
            System.out.print("Введіть номер команди (1-4): ");
            if (!scanner.hasNextInt()) {
                String invalidInput = scanner.nextLine();
                System.out.println("Некоректне введення \"" + invalidInput + "\". Введіть число від 1 до 4.");
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addOrdersFromCsv();
                    break;
                case 2:
                    viewAllOrders();
                    break;
                case 3:
                    dropAllOrders();
                    break;
                case 4:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Номер команди некоректний. Спробуй ще.");
            }
        }
    }

    private void addOrdersFromCsv() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
            getClass().getClassLoader().getResourceAsStream("retail.csv"),
            StandardCharsets.UTF_8)
        )) {
            List<String[]> records = reader.readAll();
            
            records.remove(0); // Видалити перший рядок з назвами стовпців

            List<RetailOrder> orderList = new ArrayList<>();
            for (String[] record : records) {
                RetailOrder order = new RetailOrder(
                    record[0], // storeName
                    record[1], // supplier
                    record[2], // logisticsCompany
                    record[3], // orderDate
                    record[4], // productBatch
                    record[5], // mainProductCategory
                    record[6], // wholesaleDiscount
                    record[7], // warehouseAddress
                    record[8], // warehousePhone
                    record[9]  // deliveryConditions
                );

                orderList.add(order);
            }
            
            // Очистити існуючі замовлення, щоб уникнути дублювання при повторному імпорті
            retailOrderRepository.deleteAll();
            retailOrderRepository.saveAll(orderList);
            System.out.println(orderList.size() + " документів з замовленнями Retail завантажено з CSV.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не вдалось завантажити замовлення з CSV.");
        }
    }

    private void viewAllOrders() {
        List<RetailOrder> orders = retailOrderRepository.findAll();
        if (orders.isEmpty()) {
            System.out.println("Документи з замовленнями не знайдено.");
        } else {
            System.out.println("Знайдено " + orders.size() + " документів замовлень:");
            orders.forEach(System.out::println);
        }
    }

    private void dropAllOrders() {
        retailOrderRepository.deleteAll();
        System.out.println("Замовлення видалено.");
    }
}
