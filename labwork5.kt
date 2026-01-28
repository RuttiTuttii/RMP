import kotlin.math.abs

// 5.1.1
fun tempScale() {
    print("Введите шаг: ")
    val step = readln().toInt()

    println("C\tF")
    for (c in 100 downTo -50 step step) {
        println("$c\t${c * 1.8 + 32}")
    }
}

// 5.2.1
fun squaresTable() {
    print("\t")
    for (i in 0..9) print("$i\t")
    println()

    for (t in 1..9) {
        print("$t\t")
        for (u in 0..9) {
            val n = t * 10 + u
            print("${n * n}\t")
        }
        println()
    }
}

// 5.3.1
fun safeDivide() {
    print("Введите делимое: ")
    val a = readln().toDouble()

    var b: Double
    while (true) {
        print("Введите делитель: ")
        b = readln().toDouble()
        if (b != 0.0) break
        println("Делить на ноль нельзя")
    }

    println("Результат: ${a / b}")
}

// 5.4.1
fun shop() {
    print("Введите сумму покупки: ")
    val price = readln().toDouble()

    var paid: Double
    do {
        print("Введите внесенную сумму: ")
        paid = readln().toDouble()
        if (paid < price) {
            println("Не хватает ${price - paid}")
        }
    } while (paid < price)

    if (paid == price) println("Спасибо!")
    else println("Возьмите сдачу ${paid - price}")
}

// 5.5
fun queens() {
    val q = Array(8) { IntArray(2) }

    for (i in 0..7) {
        print("Введите координаты ферзя ${i + 1}: ")
        val p = readln().split(" ")
        q[i][0] = p[0].toInt()
        q[i][1] = p[1].toInt()
    }

    for (i in 0..6) {
        for (j in i + 1..7) {
            if (
                q[i][0] == q[j][0] ||
                q[i][1] == q[j][1] ||
                abs(q[i][0] - q[j][0]) == abs(q[i][1] - q[j][1])
            ) {
                println("Ферзи бьют друг друга")
                return
            }
        }
    }

    println("Ферзи расставлены правильно")
}

fun main() {
    tempScale()
    squaresTable()
    safeDivide()
    shop()
    queens()
}