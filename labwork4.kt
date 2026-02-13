import java.util.Scanner
import kotlin.math.*

fun main() {
    val scanner = Scanner(System.`in`)

    yearMonthInfo(scanner)
    println("-----")

    triangleCheck(scanner)
    println("-----")

    currencyConverter(scanner)
    println("-----")

    functionCalculation(scanner)
    println("-----")

    purchaseCalculation(scanner)
}

/* 5.1 год, месяц, високосность, дни, сезон */
fun yearMonthInfo(scanner: Scanner) {
    print("введите год: ")
    val year = scanner.nextInt()

    print("введите номер месяца (1-12): ")
    val month = scanner.nextInt()

    if (month !in 1..12) {
        println("некорректный номер месяца")
        return
    }

    // проверка на високосный год
    val leapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    println("високосный год: $leapYear")

    // количество дней в месяце
    val days = when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> if (leapYear) 29 else 28
        else -> 0
    }
    println("количество дней в месяце: $days")

    // сезон
    val season = when (month) {
        12, 1, 2 -> "зима"
        3, 4, 5 -> "весна"
        6, 7, 8 -> "лето"
        else -> "осень"
    }
    println("сезон: $season")
}

/* 5.2 проверка треугольника */
fun triangleCheck(scanner: Scanner) {
    print("введите сторону a: ")
    val a = scanner.nextDouble()
    print("введите сторону b: ")
    val b = scanner.nextDouble()
    print("введите сторону c: ")
    val c = scanner.nextDouble()

    if (a + b <= c || a + c <= b || b + c <= a) {
        println("треугольник не существует")
        return
    }

    println("треугольник существует")

    when {
        a == b && b == c -> println("треугольник равносторонний")
        a == b || a == c || b == c -> println("треугольник равнобедренный")
        else -> println("треугольник разносторонний")
    }
}

/* 5.3 конвертер валют */
fun currencyConverter(scanner: Scanner) {
    val dollarRate = 90.0
    val euroRate = 98.0

    print("введите сумму в рублях: ")
    val rubles = scanner.nextDouble()
    scanner.nextLine()

    print("введите валюту (usd / eur): ")
    val currency = scanner.nextLine().lowercase()

    val result = when (currency) {
        "usd" -> rubles / dollarRate
        "eur" -> rubles / euroRate
        else -> {
            println("некорректная валюта")
            println("сумма: %.2f".format(rubles))
            return
        }
    }

    println("сумма в валюте: %.2f".format(result))
}

/* 5.4 вычисление функции */
fun functionCalculation(scanner: Scanner) {
    print("введите a: ")
    val a = scanner.nextDouble()
    print("введите x: ")
    val x = scanner.nextDouble()

    val result = try {
        when {
            x < 0 -> a + x.pow(3)
            x < 3 -> sin(x) + cos(x)
            x < 5 -> {
                if (a == x) throw ArithmeticException()
                1 / (a - x)
            }
            else -> {
                if (x - a < 0) throw ArithmeticException()
                sqrt(x - a)
            }
        }
    } catch (e: Exception) {
        println("вычисление невозможно")
        return
    }

    println("результат: %.3f".format(result))
}

/* 5.5 расчет покупки и сдачи */
fun purchaseCalculation(scanner: Scanner) {
    print("введите сумму покупки: ")
    val purchase = scanner.nextDouble()

    print("введите внесенную сумму: ")
    val paid = scanner.nextDouble()

    val discount = when {
        purchase > 5000 -> 0.10
        purchase > 1000 -> 0.05
        else -> 0.0
    }

    val finalPrice = purchase * (1 - discount)
    val roundedPrice = String.format("%.2f", finalPrice).toDouble()

    println("сумма к оплате: %.2f".format(roundedPrice))

    when {
        paid == roundedPrice -> println("Спасибо!")
        paid > roundedPrice -> println("Возьмите сдачу: %.2f".format(paid - roundedPrice))
        else -> println("Требуется доплатить: %.2f".format(roundedPrice - paid))
    }
}