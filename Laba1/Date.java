package GUU.Laba1;

public class Date {

    private int year;   // год
    private int month;  // месяц
    private int day;    // день

    // 1. Конструктор по умолчанию
    public Date() {
        this(2000, 1, 1);
    }

    // 2. Основной конструктор — три числа
    public Date(int year, int month, int day) {
        if (!isValidDate(year, month, day)) {
            throw new IllegalArgumentException(
                    "Некорректная дата: " + year + "." + month + "." + day);
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // 3. Конструктор из строки "yyyy.mm.dd"
    public Date(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Дата не может быть пустой");
        }

        String cleaned = dateStr.trim().replaceAll("\\s+", "");  // убираем все пробелы
        String[] parts = cleaned.split("\\.");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Ожидается три числа, разделённые точками (пример: 2025.6.9)");
        }

        int y, m, d;
        try {
            y = Integer.parseInt(parts[0]);
            m = Integer.parseInt(parts[1]);
            d = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Не удалось распознать числа в строке: " + dateStr);
        }

        if (!isValidDate(y, m, d)) {
            throw new IllegalArgumentException("Некорректная дата: " + y + "." + m + "." + d);
        }

        this.year = y;
        this.month = m;
        this.day = d;
    }

    // 4. Конструктор копирования
    public Date(Date other) {
        this.year = other.year;
        this.month = other.month;
        this.day = other.day;
    }

    // ───────────────────────────────────────────────
    // Вспомогательные статические методы
    // ───────────────────────────────────────────────

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private static int daysInMonth(int year, int month) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        return 31;
    }

    private static boolean isValidDate(int year, int month, int day) {
        if (year < 1 || year > 9999) return false;
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > daysInMonth(year, month)) return false;
        return true;
    }

    // ───────────────────────────────────────────────
    // Основные операции
    // ───────────────────────────────────────────────

    public Date addDays(int days) {
        Date result = new Date(this);
        int remaining = days;

        while (remaining > 0) {
            int daysLeft = daysInMonth(result.year, result.month) - result.day + 1;
            if (remaining < daysLeft) {
                result.day += remaining;
                remaining = 0;
            } else {
                remaining -= daysLeft;
                result.day = 1;
                result.month++;
                if (result.month > 12) {
                    result.month = 1;
                    result.year++;
                }
            }
        }

        while (remaining < 0) {
            result.month--;
            if (result.month < 1) {
                result.month = 12;
                result.year--;
            }
            result.day = daysInMonth(result.year, result.month);
            remaining++;
        }

        return result;
    }

    public Date subtractDays(int days) {
        return addDays(-days);
    }

    public int daysBetween(Date other) {
        Date d1 = new Date(this);
        Date d2 = new Date(other);
        int count = 0;
        int sign = 1;

        if (d1.isBefore(d2)) {
            sign = -1;
            Date temp = d1;
            d1 = d2;
            d2 = temp;
        }

        while (!d2.equals(d1)) {
            d2 = d2.addDays(1);
            count++;
        }

        return count * sign;
    }

    public boolean isBefore(Date other) {
        if (this.year != other.year) return this.year < other.year;
        if (this.month != other.month) return this.month < other.month;
        return this.day < other.day;
    }

    public boolean isAfter(Date other) {
        return other.isBefore(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Date other = (Date) obj;
        return year == other.year && month == other.month && day == other.day;
    }

    // ───────────────────────────────────────────────
    // Геттеры и сеттеры
    // ───────────────────────────────────────────────

    public int getYear()  { return year;  }
    public int getMonth() { return month; }
    public int getDay()   { return day;   }

    public void setYear(int year) {
        if (isValidDate(year, this.month, this.day)) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Невозможно установить такой год");
        }
    }

    public void setMonth(int month) {
        if (isValidDate(this.year, month, this.day)) {
            this.month = month;
        } else {
            throw new IllegalArgumentException("Невозможно установить такой месяц");
        }
    }

    public void setDay(int day) {
        if (isValidDate(this.year, this.month, day)) {
            this.day = day;
        } else {
            throw new IllegalArgumentException("Невозможно установить такой день");
        }
    }

    // ───────────────────────────────────────────────
    // Вывод
    // ───────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format("%04d.%02d.%02d", year, month, day);
    }

    public void display() {
        System.out.println(this);
    }

    }