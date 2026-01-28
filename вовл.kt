import kotlin.math.abs

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