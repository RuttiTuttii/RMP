import kotlin.math.abs

fun queens(auto: Int = 0) {
    val row = IntArray(8)
    val col = IntArray(8)

    // ---------- Автоввод -----------
    val correct = listOf(
        listOf(1,5), listOf(2,8), listOf(3,6), listOf(4,3),
        listOf(5,7), listOf(6,2), listOf(7,4), listOf(8,1)
    )

    val wrong = listOf(
        listOf(1,1), listOf(2,2), listOf(3,3), listOf(4,4),
        listOf(5,5), listOf(6,6), listOf(7,7), listOf(8,8)
    )

    val chosen = when(auto) {
        1 -> correct
        2 -> wrong
        else -> null
    }

    for (i in 0..7) {
        if (chosen != null) {
            // автоввод
            row[i] = chosen[i][0]
            col[i] = chosen[i][1]
            println("Ферзь ${i+1}: строка ${row[i]} столбец ${col[i]}")
        } else {
            // обычный ввод руками
            print("Ферзь ${i+1} (строка столбец): ")
            val p = readln().trim().split(" ")
            row[i] = p[0].toInt()
            col[i] = p[1].toInt()
        }
    }

    for (i in 0..6) {
        for (j in i + 1..7) {
            if (row[i] == row[j]) {
                println("Бьются по строке: ${i + 1} и ${j + 1}")
                return
            }
            if (col[i] == col[j]) {
                println("Бьются по столбцу: ${i + 1} и ${j + 1}")
                return
            }
            if (abs(row[i] - row[j]) == abs(col[i] - col[j])) {
                println("Бьются по диагонали: ${i + 1} и ${j + 1}")
                return
            }
        }
    }

    println("Ферзи расставлены правильно")
}

fun main() {
    println("=== Тест правильной расстановки ===")
    queens(auto = 1)

    println("\n=== Тест неправильной расстановки ===")
    queens(auto = 2)
}

fun queens() {
    val row = IntArray(8)
    val col = IntArray(8)

    for (i in 0..7) {
        print("Введите строку и столбец ферзя ${i + 1}: ")
        val p = readln().split(" ")
        row[i] = p[0].toInt()
        col[i] = p[1].toInt()
    }

    for (i in 0..6) {
        for (j in i + 1..7) {

            // одна строка
            if (row[i] == row[j]) {
                println("Ферзи бьют друг друга")
                return
            }

            // один столбец
            if (col[i] == col[j]) {
                println("Ферзи бьют друг друга")
                return
            }

            // диагональ
            if (abs(row[i] - row[j]) == abs(col[i] - col[j])) {
                println("Ферзи бьют друг друга")
                return
            }
        }
    }

    println("Ферзи расставлены правильно")
}