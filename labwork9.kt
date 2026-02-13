import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    listOfStrings(scanner)
    println("-----")

    listOfInts(scanner)
    println("-----")

    mapWork(scanner)
    println("-----")

    setWork()
}

/* 5.1 создание, заполнение и вывод списка строк */
fun listOfStrings(scanner: Scanner) {
    val list = mutableListOf<String>()

    // добавление 3 элементов программно
    list.add("alpha")
    list.add("beta")
    list.add("gamma")

    print("введите количество элементов n: ")
    val n = scanner.nextInt()
    scanner.nextLine()

    // ввод n элементов с клавиатуры
    for (i in 1..n) {
        print("введите строку $i: ")
        list.add(scanner.nextLine())
    }

    // вывод элементов списка
    for (i in list.indices) {
        println("${i + 1} - ${list[i]}")
    }

    println("количество элементов списка: ${list.size}")
}

/* 5.2 обработка списков целых чисел */
fun listOfInts(scanner: Scanner) {
    val numbers = mutableListOf<Int>()

    print("введите количество элементов n: ")
    val n = scanner.nextInt()

    for (i in 1..n) {
        print("введите число $i: ")
        numbers.add(scanner.nextInt())
    }

    // индекс элемента со значением 100
    val index100 = numbers.indexOf(100)
    if (index100 != -1) {
        println("индекс элемента со значением 100: $index100")
    } else {
        println("элемент со значением 100 не найден")
    }

    // сумма элементов
    println("сумма элементов: ${numbers.sum()}")

    // среднее значение
    println("среднее значение: ${numbers.average()}")

    // все ли числа больше нуля
    println("все ли числа больше нуля: ${numbers.all { it > 0 }}")

    // все нечетные значения
    val oddNumbers = numbers.filter { it % 2 != 0 }
    println("нечетные значения: $oddNumbers")
}

/* 5.3 и 5.4 работа со словарем */
fun mapWork(scanner: Scanner) {
    val map = mutableMapOf<Int, String>()

    // добавление 3 элементов программно
    map[1] = "один"
    map[2] = "два"
    map[3] = "три"

    print("введите количество элементов n: ")
    val n = scanner.nextInt()
    scanner.nextLine()

    // ввод n элементов с клавиатуры
    for (i in 1..n) {
        print("введите ключ (int): ")
        val key = scanner.nextInt()
        scanner.nextLine()
        print("введите значение (string): ")
        val value = scanner.nextLine()
        map[key] = value
    }

    // вывод словаря
    for ((key, value) in map) {
        println("$key - $value")
    }
    println("количество элементов словаря: ${map.size}")

    // поиск по ключу
    print("введите ключ для поиска: ")
    val searchKey = scanner.nextInt()
    scanner.nextLine()

    if (map.containsKey(searchKey)) {
        println("ключ найден, значение: ${map[searchKey]}")
    } else {
        println("указанный ключ отсутствует в словаре")
    }

    // подсчет совпадений значений
    print("введите значение для подсчета совпадений: ")
    val searchValue = scanner.nextLine()
    val count = map.values.count { it == searchValue }
    println("количество совпадений значения: $count")

    // удаление элемента по ключу
    print("введите ключ для удаления: ")
    val removeKey = scanner.nextInt()
    map.remove(removeKey)

    println("словарь после удаления:")
    for ((key, value) in map) {
        println("$key - $value")
    }
}

/* 5.5 обработка множеств */
fun setWork() {
    // множества студентов, не сдавших зачет
    val teacher1 = setOf(
        "иванов и.и.",
        "петров п.п.",
        "сидоров с.с."
    )

    val teacher2 = setOf(
        "петров п.п.",
        "кузнецов к.к.",
        "смирнова а.а."
    )

    // все студенты, отправленные на пересдачу
    val allStudents = teacher1 union teacher2
    println("всего студентов на пересдаче: ${allStudents.size}")
    println(allStudents)

    // студенты, не сдавшие оба зачета
    val bothFailed = teacher1 intersect teacher2
    println("не сдали оба зачета: ${bothFailed.size}")
    println(bothFailed)

    // студенты, не сдавшие только один зачет
    val onlyOneFailed = allStudents subtract bothFailed
    println("не сдали только один зачет: ${onlyOneFailed.size}")
    println(onlyOneFailed)
}