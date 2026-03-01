package GUU.Laba2;

/**
 * Базовый класс Pair — пара вещественных чисел
 * Согласно заданию: определяем методы изменения полей и вычисления произведения чисел
 */
class Pair {
    // Поля класса — защищённые, чтобы RightAngled мог к ним обращаться
    protected double a;  // в RightAngled — первый катет
    protected double b;  // в RightAngled — второй катет

    // Конструктор по умолчанию
    public Pair() {
        this.a = 0.0;
        this.b = 0.0;
    }

    // Конструктор с параметрами
    public Pair(double a, double b) {
        setA(a);   // вызываем сеттер — там проверка на > 0
        setB(b);
    }

    // Конструктор копирования
    public Pair(Pair other) {
        this.a = other.a;
        this.b = other.b;
    }

    // Геттеры
    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }


    // Если значение ≤ 0 — выводим сообщение и устанавливаем 0(заместо ошибки)
    public void setA(double a) {
        if (a > 0) {
            this.a = a;
        } else {
            System.out.println("Ошибка: значение a должно быть положительным");
            this.a = 0.0;
        }
    }

    public void setB(double b) {
        if (b > 0) {
            this.b = b;
        } else {
            System.out.println("Ошибка: значение b должно быть положительным");
            this.b = 0.0;
        }
    }

    // Метод вычисления произведения чисел
    public double multiply() {
        return a * b;
    }

    // Метод вывода информации на экран
    public void display() {
        System.out.printf("Pair: a = %.2f, b = %.2f\n", a, b); // printf для удобства с сцепификатором до сотых
    }

    // Переопределение toString
    @Override
    public String toString() {
        return String.format("Pair(a=%.2f, b=%.2f)", a, b);
    }
}

/**
 * Производный класс RightAngled — прямоугольный треугольник
 * Наследуется от Pair, a и b как катеты
 */
class RightAngled extends Pair {

    // Конструктор с параметрами
    public RightAngled(double leg1, double leg2) {
        super(leg1, leg2);  // вызываем родительский конструктор
    }

    // Конструктор копирования
    public RightAngled(RightAngled other) {
        super(other);  // вызываем конструктор копирования родителя
    }

    // геттер гипотенузы
    public double getHypotenuse() {
        if (a <= 0 || b <= 0) {
            System.out.println("Ошибка: катеты должны быть положительными");
            return 0.0;
        }
        return Math.sqrt(a * a + b * b);
    }

    // геттер площади треугольника
    public double getArea() {
        if (a <= 0 || b <= 0) {
            System.out.println("Ошибка: катеты должны быть положительными");
            return 0.0;
        }
        return (a * b) / 2.0;
    }

    // Переопределяем метод display
    @Override
    public void display(toString()) {
        super.display(toString());  // выводим катеты из родителя
        System.out.printf("Гипотенуза:   %.2f\n", getHypotenuse());
        System.out.printf("Площадь:      %.2f\n", getArea());
    }

    // Переопределяем toString
    @Override
    public String toString() {
        return String.format("RightAngled(катет a=%.2f, катет b=%.2f, гипотенуза=%.2f, площадь=%.2f)",
                a, b, getHypotenuse(), getArea());
    }

}

