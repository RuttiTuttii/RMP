import kotlin.math.PI
import kotlin.math.pow

fun arithmeticOperations() {
    print("Введите a: ")
    val a = readLine()!!.toInt()

    print("Введите b: ")
    val b = readLine()!!.toInt()

    println("$a+$b=${a + b}")
    println("$a-$b=${a - b}")
    println("$a*$b=${a * b}")
    println("$a/$b=${a / b}")
    println("$a%$b=${a % b}")
}

fun bmiCalculation() {
    print("Введите имя: ")
    val name = readLine()!!

    print("Введите рост (см): ")
    val heightCm = readLine()!!.toInt()

    print("Введите массу тела (кг): ")
    val weight = readLine()!!.toDouble()

    val heightM = heightCm / 100.0
    val bmi = weight / (heightM * heightM)

    println("$name, ваш ИМТ=${"%.2f".format(bmi)}")
}

fun electronicClock() {
    print("Введите количество секунд с начала суток: ")
    val n = readLine()!!.toInt()

    val secondsInDay = 24 * 60 * 60
    val time = n % secondsInDay

    val hours = time / 3600
    val minutes = (time % 3600) / 60
    val seconds = time % 60

    println(String.format("%02d:%02d:%02d", hours, minutes, seconds))
}

fun leapYearCheck() {
    print("Введите год: ")
    val year = readLine()!!.toInt()

    val isLeap = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0
    println(isLeap)
}

fun ringArea() {
    print("Введите внешний радиус: ")
    val rOuter = readLine()!!.toDouble()

    print("Введите внутренний радиус: ")
    val rInner = readLine()!!.toDouble()

    val area = PI * (rOuter.pow(2) - rInner.pow(2))
    println(String.format("%.3f", area))
}

fun main() {
    println("Задание 5.1")
    arithmeticOperations()
    println()

    println("Задание 5.2")
    bmiCalculation()
    println()

    println("Задание 5.3")
    electronicClock()
    println()

    println("Задание 5.4")
    leapYearCheck()
    println()

    println("Задание 5.5")
    ringArea()
}
fun bmiCalculation() {
    print("Введите имя: ")
    val name = readLine()!!

    print("Введите рост (см): ")
    val heightCm = readLine()!!.toInt()

    print("Введите массу тела (кг): ")
    val weight = readLine()!!.toDouble()

    val heightM = heightCm / 100.0
    val bmi = weight / (heightM * heightM)

    val status = when {
        bmi < 18.5 -> "дефицит массы тела"
        bmi < 25.0 -> "норма"
        bmi < 30.0 -> "избыточная масса тела"
        else -> "ожирение"
    }

    println("$name, ваш ИМТ=${"%.2f".format(bmi)} ($status)")
}

