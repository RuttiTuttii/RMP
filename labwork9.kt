import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    namesList(scanner)
    println("-----")

    intListProcessing(scanner)
    println("-----")

    bookAuthorMap(scanner)
    println("-----")

    studentsSets()
}

/* 5.1 создание, заполнение и вывод списка (имена) */
fun namesList(scanner: Scanner) {
    val names = mutableListOf<String>()

    // добавление 3 элементов программно
    names.add("Анна")
    names.add("Иван")
    names.add("Мария")

    print("введите количество элементов n: ")
    val n = scanner.nextInt()
    scanner.nextLine()

    // ввод n элементов с клавиатуры
    for (i in 1..n) {
        print("введите имя $i: ")
        names.add(scanner.nextLine())
    }

    // вывод списка
    for (i in names.indices) {
        println("${i + 1} - ${names[i]}")
    }

    println("количество элементов списка: ${names.size}")
}

/* 5.2 обработка списка целых чисел */
fun intListProcessing(scanner: Scanner) {
    val numbers = mutableListOf<Int>()

    print("введите количество элементов n: ")
    val n = scanner.nextInt()

    for (i in 1..n) {
        print("введите число $i: ")
        numbers.add(scanner.nextInt())
    }

    // поиск индекса элемента со значением 100
    val index100 = numbers.indexOf(100)
    if (index100 != -1) {
        println("индекс элемента со значением 100: $index100")
    } else {
        println("элемент со значением 100 не найден")
    }

    // сумма элементов
    println("сумма элементов коллекции: ${numbers.sum()}")

    // среднее значение
    println("среднее значение элементов коллекции: ${numbers.average()}")

    // проверка, все ли числа больше нуля
    println("все ли числа больше нуля: ${numbers.all { it > 0 }}")

    // вывод нечетных элементов
    val oddNumbers = numbers.filter { it % 2 != 0 }
    println("нечетные значения элементов: $oddNumbers")
}

/* 5.3 и 5.4 словарь (книга – автор) */
fun bookAuthorMap(scanner: Scanner) {
    val books = mutableMapOf<String, String>()

    // добавление 3 элементов программно
    books["Мастер и Маргарита"] = "Булгаков"
    books["Война и мир"] = "Толстой"
    books["Преступление и наказание"] = "Достоевский"

    print("введите количество элементов n: ")
    val n = scanner.nextInt()
    scanner.nextLine()

    // ввод n элементов с клавиатуры
    for (i in 1..n) {
        print("введите название книги: ")
        val book = scanner.nextLine()
        print("введите автора: ")
        val author = scanner.nextLine()
        books[book] = author
    }

    // вывод словаря
    for ((book, author) in books) {
        println("$book - $author")
    }
    println("количество элементов словаря: ${books.size}")

    // поиск по ключу
    print("введите название книги для поиска: ")
    val searchBook = scanner.nextLine()

    if (books.containsKey(searchBook)) {
        println("книга найдена, автор: ${books[searchBook]}")
    } else {
        println("указанная книга отсутствует в словаре")
    }

    // подсчет совпадений по автору
    print("введите автора для подсчета совпадений: ")
    val searchAuthor = scanner.nextLine()
    val count = books.values.count { it == searchAuthor }
    println("количество совпадений значения: $count")

    // удаление элемента по ключу
    print("введите название книги для удаления: ")
    val removeBook = scanner.nextLine()
    books.remove(removeBook)

    println("словарь после удаления:")
    for ((book, author) in books) {
        println("$book - $author")
    }
}

/* 5.5 обработка множеств */
fun studentsSets() {
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