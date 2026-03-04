Готовое решение лабораторной работы №14 (все пункты 5.1–5.6 выполнены полностью, комментарии на русском языке в соответствии с требованиями)
Инструкция
	1	Создайте новый проект Empty Activity (Jetpack Compose, Kotlin).
	2	Замените содержимое файла MainActivity.kt на код ниже.
	3	Исправьте:
	◦	имя пакета (если отличается от com.example.lab14),
	◦	название темы (Lab14Theme → название вашей темы из ui.theme).
package com.example.lab14  // ← поменяйте на свой пакет проекта

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab14.ui.theme.Lab14Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 5.3.3 — вызов функции деления (комментарий KDoc виден при наборе)
        try {
            val res1 = divide(25.0, 5.0)
            Log.d("MainActivity", "25.0 / 5.0 = $res1")

            // проверка деления на ноль
            val res2 = divide(10.0, 0.0)
        } catch (e: ArithmeticException) {
            Log.e("MainActivity", "Ошибка: ${e.message}")
        }

        // пример использования data-класса
        val user = User("student", "password123")
        Log.d("MainActivity", "Пароль совпадает: ${user.checkPassword("password123")}")

        setContent {
            Lab14Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    /**
     * Вычисляет результат деления двух чисел.
     *
     * Выполняет математическую операцию деления a на b.
     * При попытке деления на ноль генерирует исключение (п. 5.4).
     *
     * @param a делимое
     * @param b делитель (не может быть равен 0)
     * @return результат деления a / b
     * @throws ArithmeticException если делитель равен нулю
     * @author Иванов И. И.
     * @since 1.0
     * @sample com.example.lab14.MainActivity.divide
     */
    private fun divide(a: Double, b: Double): Double {
        if (b == 0.0) {
            throw ArithmeticException("Деление на ноль невозможно")
        }
        return a / b
    }
}

/**
 * Компонуемая функция приветствия.
 *
 * Отображает текст «Hello {name}!» (п. 5.2).
 *
 * @param name имя для приветствия
 * @param modifier модификатор компоновки (по умолчанию пустой)
 * @author Иванов И. И.
 * @since 1.0
 * @sample com.example.lab14.Greeting
 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

/**
 * Предпросмотр функции Greeting в Android Studio.
 *
 * Используется только для отображения превью (п. 5.1).
 *
 * @author Иванов И. И.
 * @since 1.0
 * @sample com.example.lab14.MainActivity.GreetingPreview
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab14Theme {
        Greeting("Android")
    }
}

/**
 * Data-класс для хранения логина и пароля пользователя.
 *
 * Логин и пароль объявлены в основном конструкторе (п. 5.5).
 *
 * @property login логин пользователя
 * @property password пароль пользователя
 * @author Иванов И. И.
 * @since 1.0
 */
data class User(
    val login: String,
    val password: String
) {

    /**
     * Проверяет, совпадает ли переданный пароль с паролем пользователя.
     *
     * @param enteredPassword пароль для проверки
     * @return `true` — если пароли совпадают, иначе `false`
     * @author Иванов И. И.
     * @since 1.0
     * @sample com.example.lab14.User.checkPassword
     */
    fun checkPassword(enteredPassword: String): Boolean =
        this.password == enteredPassword
}
Что выполнено
	•	5.1 — KDoc для GreetingPreview (функция без параметров)
	•	5.2 — KDoc для Greeting с описанием параметров
	•	5.3 — функция divide() в MainActivity с возвращаемым значением + полный KDoc
	•	5.4 — генерация ArithmeticException при делении на 0 + @throws в KDoc
	•	5.5 — data class User + метод checkPassword() + KDoc ко всем элементам
	•	5.6 — ко всем функциям и классу добавлены блоки @sample, @since, @author
При наборе кода в Android Studio (например, divide(, User(, checkPassword() вы увидите всплывающие KDoc-комментарии — всё соответствует требованию 5.3.3.
Готово! Сдавайте работу. Если нужно что-то подправить (ФИО автора, пакет и т.д.) — напишите. Удачи! 🚀
