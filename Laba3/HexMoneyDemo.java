package GUU.Laba3;

import java.util.Scanner;

public class HexMoneyDemo {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Лабораторная работа №3, вариант 9 ===\n");
        System.out.println("Интерфейс Array, классы Hex и Money\n");


        //====пример ввода с клавиатуры

        Hex userHex = null;
        while (userHex == null) {
            System.out.println("Введите hex-число (только 0-9 и A-F, например: A1F или 1E)");
            System.out.print("→ ");
            String hexInput = scanner.nextLine().trim().toUpperCase();

            if (hexInput.isEmpty()) { // метод string проверяющий строку на пустоту
                System.out.println("Ошибка: строка не может быть пустой. Повторите ввод.");
                continue;
            }
            // [0-9A-F]+  == Вся строка должна состоять только из символов 0-9 и A-F и их должно быть ≥ 1 (из за +)
            if (!hexInput.matches("[0-9A-F]+")) { // метод string дающий 1 при соответствии всех символов
                System.out.println("Ошибка: строка должна содержать только символы 0-9 и A-F.");
                System.out.println("Повторите ввод.\n");
                continue;
            }

            // дошли досюда — строка корректна
            userHex = new Hex(hexInput);
            System.out.println("Введённое hex-число:");
            userHex.display();
        }

        System.out.println("\nДальше идут автоматические тесты:\n");

        // 1. Демонстрация класса Hex
        System.out.println("--- КЛАСС HEX ---");
        Hex h1 = new Hex("A");  // 10
        Hex h2 = new Hex("B");  // 11
        System.out.println("h1: " + h1);
        System.out.println("h2: " + h2);

        // Сложение через интерфейс
        Array sumHex = h1.add(h2);
        System.out.println("Сумма h1 + h2: " + sumHex);  // ожидается 15 hex = 21 decimal

        // 2. Демонстрация класса Money
        System.out.println("\n--- КЛАСС MONEY ---");
        Money m1 = new Money(100, (byte)50);  // 100.50
        Money m2 = new Money(200, (byte)60);  // 200.60
        System.out.println("m1: " + m1);
        System.out.println("m2: " + m2);

        // Сложение через интерфейс
        Array sumMoney = m1.add(m2);
        System.out.println("Сумма m1 + m2: " + sumMoney);  // 301.10

        // 3. Полиморфизм и разные варианты вызова
        System.out.println("\n--- ПОЛИМОРФИЗМ И ВАРИАНТЫ ВЫЗОВА ---");
        Array arr1 = new Hex("1F");
        Array arr2 = new Hex("E1");
        System.out.println("arr1 (Hex): " + arr1);
        System.out.println("arr2 (Hex): " + arr2);
        System.out.println("Сумма через Array: " + arr1.add(arr2));

        Array arr3 = new Money(50, (byte)30);
        Array arr4 = new Money(40, (byte)70);
        System.out.println("arr3 (Money): " + arr3);
        System.out.println("arr4 (Money): " + arr4);
        System.out.println("Сумма через Array: " + arr3.add(arr4));

        // Попытка сложить разные типы — по заданию показать все варианты
        System.out.println("\nПопытка сложить Hex и Money:");
        Array mixed = arr1.add(arr3);  // должно вывести ошибку и вернуть null или себя
        System.out.println("Результат: " + (mixed == null ? "null" : mixed));

        // 4. Защита от некорректных данных
        System.out.println("\n--- ЗАЩИТА ОТ НЕКОРРЕКТНЫХ ДАННЫХ ---");
        new Money(100, (byte)150);  // ошибка в копейках

        // 5. Конструктор копирования (для Money)
        System.out.println("\n--- КОНСТРУКТОР КОПИРОВАНИЯ ---");
        Money original = new Money(300, (byte)40);
        Money copy = new Money(original);
        System.out.println("Оригинал: " + original);
        System.out.println("Копия: " + copy);
        copy.setRubles(400);
        System.out.println("\nПосле изменения копии:");
        System.out.println("Оригинал: " + original);
        System.out.println("Копия: " + copy);

        // 6. toString и display
        System.out.println("\n--- toString и display ---");
        h1.display();
        m1.display();

        scanner.close();
    }
}
