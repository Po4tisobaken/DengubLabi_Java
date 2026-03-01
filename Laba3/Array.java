package GUU.Laba3;

interface Array {
    // Функция поэлементного сложения — возвращает новый объект с суммой
    Array add(Array other);
}

/**
 * Класс Hex — беззнаковое шеснадцатеричное число
 */
class Hex implements Array {
    private byte[] digits = new byte[100];  // 0-15

    // Конструктор из строки hex
    public Hex(String hexStr) {
        if (hexStr == null || hexStr.isEmpty()) {
            return;  // нулевое число
        }
        hexStr = hexStr.toUpperCase().trim();
        if (!hexStr.matches("[0-9A-F]+")) {
            System.out.println("Ошибка: некорректная hex-строка");
            return;
        }
        int len = Math.min(hexStr.length(), 100);
        for (int i = 0; i < len; i++) {
            char c = hexStr.charAt(hexStr.length() - 1 - i);
            digits[i] = (byte) (c >= 'A' ? c - 'A' + 10 : c - '0');
        }
    }

    // Конструктор копирования
    public Hex(Hex other) {
        this.digits = new byte[100];              // создаю новый массив
        System.arraycopy(other.digits, 0, this.digits, 0, 100); // чтобы не прописывать копирование вручную

    }

    // Поэлементное сложение с переносом
    @Override
    public Array add(Array other) {
        if (!(other instanceof Hex o)) {
            System.out.println("Ошибка: другой объект не Hex");
            return this;  // или null
        }
        Hex result = new Hex("0");
        int carry = 0;
        for (int i = 0; i < 100; i++) {
            int sum = (digits[i] & 0xFF) + (o.digits[i] & 0xFF) + carry;
            result.digits[i] = (byte) (sum % 16);
            carry = sum / 16;
        }
        if (carry > 0) {
            System.out.println("Переполнение при сложении Hex");
        }
        return result;
    }

    // Вывод на экран
    public void display() {
        System.out.println(toString());
    }

    // Преобразование в строку
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (int i = 99; i >= 0; i--) {
            if (digits[i] == 0 && leadingZero) continue;
            leadingZero = false;
            sb.append(digits[i] < 10 ? (char) (digits[i] + '0') : (char) (digits[i] - 10 + 'A'));
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

/**
 * Класс Money
 * Два поля: long руб + byte коп
**/
class Money implements Array {
    private long rubles;
    private byte kopecks;

    // Внутренний массив для поэлементного сложения
    private byte[] digits = new byte[100];

    // Конструктор с параметрами
    public Money(long rubles, byte kopecks) {
        if (kopecks < 0 || kopecks > 99) {
            System.out.println("Ошибка: копейки должны быть 0-99");
            this.rubles = 0;
            this.kopecks = 0;
            return;
        }
        this.rubles = rubles;
        this.kopecks = kopecks;
        toDigits();  // конвертируем в массив цифр
    }

    // Конструктор копирования
    public Money(Money other) {
        this.rubles = other.rubles;
        this.kopecks = other.kopecks;
        this.digits = new byte[100];
        System.arraycopy(other.digits, 0, this.digits, 0, 100);
    }

    // Конвертация руб + коп в массив цифр
    private void toDigits() {
        long totalKopecks = rubles * 100 + kopecks;
        for (int i = 0; i < 100 && totalKopecks > 0; i++) {
            digits[i] = (byte) (totalKopecks % 10);
            totalKopecks /= 10;
        }
    }

    // Конвертация массива обратно в руб + коп (для toString)
    private void fromDigits() {
        long total = 0;
        long multiplier = 1;
        for (int i = 0; i < 100; i++) {
            total += digits[i] * multiplier;
            multiplier *= 10;
        }
        this.rubles = total / 100;
        this.kopecks = (byte) (total % 100);
    }

    // Геттеры
    public long getRubles() {
        return rubles;
    }

    public byte getKopecks() {
        return kopecks;
    }

    // Сеттеры с проверкой
    public void setRubles(long rubles) {
        if (rubles >= 0) {
            this.rubles = rubles;
            toDigits();
        } else {
            System.out.println("Ошибка: рубли должны быть >= 0");
        }
    }

    public void setKopecks(byte kopecks) {
        if (kopecks >= 0 && kopecks <= 99) {
            this.kopecks = kopecks;
            toDigits();
        } else {
            System.out.println("Ошибка: копейки 0-99");
        }
    }

    // Поэлементное сложение массивов цифр с переносом
    @Override
    public Array add(Array other) {
        if (!(other instanceof Money o)) {
            System.out.println("Ошибка: другой объект не Money");
            return this;
        }
        Money result = new Money(0, (byte) 0);
        int carry = 0;
        for (int i = 0; i < 100; i++) {
            int sum = (digits[i] & 0xFF) + (o.digits[i] & 0xFF) + carry;
            result.digits[i] = (byte) (sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) {
            System.out.println("Переполнение при сложении Money");
        }
        result.fromDigits();
        return result;
    }


    // Вывод на экран
    public void display() {
        System.out.println(toString());
    }

    // toString
    @Override
    public String toString() {
        return rubles + "." + (kopecks < 10 ? "0" + kopecks : kopecks);
    }
}
