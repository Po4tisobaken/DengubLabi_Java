package GUU.Laba1;

import java.util.Scanner;

public class Main {

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Тестирование класса Date ===\n");

        // ───────────────────────────────────────────────
        // Пример ввода даты пользователем (один раз)
        // ───────────────────────────────────────────────
        System.out.println("Сначала введите свою дату (формат: yyyy.mm.dd)");
        System.out.print("→ ");
        String input = scanner.nextLine().trim();

        Date userDate;
        try {
            userDate = new Date(input);
            System.out.println("Вы ввели: " + userDate);
            System.out.println("  Високосный год? → " + Date.isLeapYear(userDate.getYear()));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.out.println("Будет использована дата по умолчанию для тестов.");
            userDate = new Date(2024, 3, 1); // запасная дата
        }

        System.out.println("\nДальше идут автоматические тесты:\n");

        // 1. Создание разными способами
        Date d1 = new Date(2024, 2, 29);              // високосный
        Date d2 = new Date("2025.12.31");
        Date d3 = new Date(d1);                       // копия

        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        System.out.println("d3 (копия d1) = " + d3);
        System.out.println("userDate (ваша дата) = " + userDate);

        // 2. Проверка високосности
        System.out.println("\n2024 високосный? " + Date.isLeapYear(2024));   // true
        System.out.println("2025 високосный? " + Date.isLeapYear(2025));     // false

        // 3. Прибавление / вычитание дней (пример на d1)
        System.out.println("\nПрибавляем 10 дней к d1:");
        Date d1plus10 = d1.addDays(10);
        d1plus10.display();   // ожидается 2024.03.10

        System.out.println("\nВычитаем 40 дней из d2:");
        Date d2minus40 = d2.subtractDays(40);
        d2minus40.display();  // ожидается примерно 2025.11.21

        // 4. Разница в днях
        System.out.println("\nДней между d1 и d2: " + d1.daysBetween(d2));
        System.out.println("Дней между d2 и d1: " + d2.daysBetween(d1));

        // 5. Сравнение
        System.out.println("\nd1 < d2  → " + d1.isBefore(d2));
        System.out.println("d2 > d1  → " + d2.isAfter(d1));
        System.out.println("d1 == d3 → " + d1.equals(d3));

        // 6. Изменение поля (пример)
        System.out.println("\nМеняем месяц у d1 на июнь:");
        try {
            d1.setMonth(6);
            d1.display();         // 2024.06.29
        } catch (IllegalArgumentException e) {
            System.out.println("Не удалось изменить: " + e.getMessage());
        }

        // 7. Проверка некорректной даты
        System.out.println("\nПопытка создать 29 февраля 2025 (должна быть ошибка):");
        try {
            Date bad = new Date(2025, 2, 29);
            System.out.println("Это не должно напечататься");
        } catch (IllegalArgumentException e) {
            System.out.println("Ожидаемая ошибка: " + e.getMessage());
        }

        System.out.println("\nТестирование завершено.");
        scanner.close();
    }
}
