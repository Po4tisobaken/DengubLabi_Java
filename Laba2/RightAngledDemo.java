package GUU.Laba2;

import java.util.Scanner;

public class RightAngledDemo {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Лабораторная работа №2, вариант 9 ===\n");
        System.out.println("Классы Pair и RightAngled (прямоугольный треугольник)\n");

        // ===========Один пример ввода с клавиатуры

        System.out.println("Введите два катета прямоугольного треугольника");
        double leg1, leg2;

        System.out.print("Катет a: ");
        try {
            leg1 = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода → используется значение по умолчанию 3.0");
            leg1 = 3.0;
        }

        System.out.print("Катет b: ");
        try {
            leg2 = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода → используется значение по умолчанию 4.0");
            leg2 = 4.0;
        }

        RightAngled userTriangle = new RightAngled(leg1, leg2);
        System.out.println("\nВведённый треугольник:");
        userTriangle.display();

        System.out.println("\nДальше идут автоматические тесты:\n");

        // 1. Демонстрация базового класса Pair
        System.out.println("--- БАЗОВЫЙ КЛАСС PAIR ---");
        Pair p1 = new Pair(5.0, 7.0);
        System.out.println("Создан объект Pair:");
        p1.display();
        System.out.println("Произведение чисел : " + p1.multiply()); // произведение двух обЪектов

        // Изменение полей
        System.out.println("\nИзменяем значения полей:");
        p1.setA(8.0);
        p1.setB(12.0);
        p1.display();

        // 2. Демонстрация производного класса RightAngled
        System.out.println("\n--- ПРОИЗВОДНЫЙ КЛАСС RIGHTANGLED ---");
        RightAngled triangle1 = new RightAngled(3.0, 4.0);
        System.out.println("Создан треугольник 3-4-5:");
        triangle1.display();

        // Изменение катетов
        System.out.println("\nИзменяем катеты:");
        triangle1.setA(5.0);
        triangle1.setB(12.0);
        triangle1.display();

        // 3. Проверка защиты от некорректных данных
        System.out.println("\n--- ЗАЩИТА ОТ НЕКОРРЕКТНЫХ ДАННЫХ ---");
        System.out.println("Попытка установить отрицательный катет:");
        triangle1.setA(-3.0);   // должно вывести сообщение об ошибке
        triangle1.setB(0.0);
        triangle1.display();    // значения не изменятся

        // 4. Конструктор копирования
        System.out.println("\n--- КОНСТРУКТОР КОПИРОВАНИЯ ---");
        RightAngled original = new RightAngled(6.0, 8.0);
        RightAngled copy = new RightAngled(original);
        System.out.println("Оригинал:");
        original.display();
        System.out.println("Копия:");
        copy.display();

        // Изменяем копию — оригинал остаётся прежним
        copy.setA(7.0);
        System.out.println("\nПосле изменения копии:");
        System.out.println("Оригинал:");
        original.display();
        System.out.println("Копия:");
        copy.display();

        // 5. Полиморфизм — важный принцип ООП
        System.out.println("\n--- ПОЛИМОРФИЗМ ---");
        Pair poly = new RightAngled(9.0, 12.0);
        System.out.println("Объект RightAngled через ссылку Pair:");
        poly.display();  // вызовется метод Pair.display() — только катеты

        // Приведение типов, потому что этих методов в pair нет
        if (poly instanceof RightAngled r) {
            System.out.println("\nПосле приведения к RightAngled:");
            System.out.println("Гипотенуза: " + r.getHypotenuse());
            System.out.println("Площадь:    " + r.getArea());
        }

        // 6. Демонстрация toString
        System.out.println("\n--- ДЕМОНСТРАЦИЯ toString() ---");
        System.out.println("p1:          " + p1);
        System.out.println("triangle1:   " + triangle1);
        System.out.println("user input:  " + userTriangle);

        scanner.close();
        System.out.println("\n=== Тестирование завершено ===");
    }
}
