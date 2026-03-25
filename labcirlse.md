Вот весь код в одном файле (например, MainActivity.kt), разделённый на функции по заданиям. Все текстовые подписи и строки приведены к строчным буквам в разговорном стиле, как требуется.

```kotlin
package com.example.lab22

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var currentScreen by remember { mutableStateOf("menu") }

                when (currentScreen) {
                    "menu" -> MainMenuScreen(onNavigate = { currentScreen = it })
                    "circular_infinite" -> InfiniteCircularProgressScreen()
                    "linear" -> LinearProgressScreen()
                    "multiple_linear" -> MultipleLinearProgressScreen()
                    "countdown" -> CountdownCircularProgressScreen()
                    "badge" -> BadgedBoxScreen()
                    "badge_snackbar" -> BadgedBoxWithSnackbarScreen()
                }
            }
        }
    }
}

// ==================== главное меню ====================
@Composable
fun MainMenuScreen(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onNavigate("circular_infinite") }) {
            Text("5.1 бесконечный круговой индикатор")
        }
        Button(onClick = { onNavigate("linear") }) {
            Text("5.2.1 линейный индикатор")
        }
        Button(onClick = { onNavigate("multiple_linear") }) {
            Text("5.2.2 несколько линейных индикаторов")
        }
        Button(onClick = { onNavigate("countdown") }) {
            Text("5.3 обратный отсчёт (круговой)")
        }
        Button(onClick = { onNavigate("badge") }) {
            Text("5.4.1 бейдж")
        }
        Button(onClick = { onNavigate("badge_snackbar") }) {
            Text("5.4.2 бейдж со скрытием и snackbar")
        }
    }
}

// ==================== 5.1 бесконечный круговой индикатор ====================
@Composable
fun InfiniteCircularProgressScreen() {
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text("загрузка...")
        } else {
            Button(onClick = {
                isLoading = true
                scope.launch {
                    delay(10_000) // 10 секунд
                    isLoading = false
                }
            }) {
                Text("скачать")
            }
        }
    }
}

// ==================== 5.2.1 линейный индикатор с процентами ====================
@Composable
fun LinearProgressScreen() {
    var progress by remember { mutableStateOf(0f) }
    var isDownloading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth())
        Text("загружено: ${(progress * 100).toInt()}%")
        Button(
            onClick = {
                if (!isDownloading) {
                    isDownloading = true
                    progress = 0f
                    scope.launch {
                        while (progress < 1f) {
                            val increment = Random.nextFloat() * 0.1f // случайный шаг до 10%
                            progress = (progress + increment).coerceAtMost(1f)
                            delay(100)
                        }
                        isDownloading = false
                    }
                }
            },
            enabled = !isDownloading
        ) {
            Text("скачать")
        }
    }
}

// ==================== 5.2.2 несколько линейных индикаторов с разными цветами ====================
@Composable
fun MultipleLinearProgressScreen() {
    val indicators = remember {
        mutableStateListOf(
            ProgressData(Color(0xFF4CAF50), 0f, false),  // зелёный
            ProgressData(Color(0xFF2196F3), 0f, false),  // синий
            ProgressData(Color(0xFFFF9800), 0f, false)   // оранжевый
        )
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
    ) {
        indicators.forEachIndexed { index, data ->
            Column {
                LinearProgressIndicator(
                    progress = data.progress,
                    modifier = Modifier.fillMaxWidth(),
                    color = data.color
                )
                Text("индикатор ${index + 1}: ${(data.progress * 100).toInt()}%")
                Button(
                    onClick = {
                        if (!data.isDownloading) {
                            indicators[index] = data.copy(isDownloading = true, progress = 0f)
                            scope.launch {
                                var currentProgress = 0f
                                while (currentProgress < 1f) {
                                    val increment = Random.nextFloat() * 0.1f
                                    currentProgress = (currentProgress + increment).coerceAtMost(1f)
                                    indicators[index] = indicators[index].copy(progress = currentProgress)
                                    delay(100)
                                }
                                indicators[index] = indicators[index].copy(isDownloading = false)
                            }
                        }
                    },
                    enabled = !data.isDownloading
                ) {
                    Text("скачать ${index + 1}")
                }
            }
        }
    }
}

data class ProgressData(val color: Color, val progress: Float, val isDownloading: Boolean)

// ==================== 5.3 круговой индикатор с обратным отсчётом и сменой цвета ====================
@Composable
fun CountdownCircularProgressScreen() {
    var remainingSeconds by remember { mutableStateOf(60) }
    var isRunning by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val progressColor = when {
        remainingSeconds in 30..60 -> Color.Green
        remainingSeconds in 10..29 -> Color.Yellow
        else -> Color.Red
    }
    val progress = remainingSeconds / 60f

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier.size(120.dp),
                strokeWidth = 8.dp,
                color = progressColor
            )
            Text(
                text = "$remainingSeconds сек",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (!isRunning) {
                    isRunning = true
                    remainingSeconds = 60
                    scope.launch {
                        while (remainingSeconds > 0) {
                            delay(1000)
                            remainingSeconds--
                        }
                        isRunning = false
                    }
                }
            },
            enabled = !isRunning
        ) {
            Text("старт")
        }
    }
}

// ==================== 5.4.1 бейдж с обновлением счётчика ====================
@Composable
fun BadgedBoxScreen() {
    var messageCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BadgedBox(
            badge = {
                if (messageCount > 0) {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text(text = if (messageCount > 99) "99+" else messageCount.toString())
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "почта",
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            messageCount += Random.nextInt(1, 10)
        }) {
            Text("обновить")
        }
    }
}

// ==================== 5.4.2 бейдж со скрытием и всплывающим сообщением ====================
@Composable
fun BadgedBoxWithSnackbarScreen() {
    var messageCount by remember { mutableStateOf(5) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BadgedBox(
                badge = {
                    if (messageCount > 0) {
                        Badge(
                            containerColor = Color(0xFF6200EE),
                            contentColor = Color.White
                        ) {
                            Text(text = if (messageCount > 99) "99+" else messageCount.toString())
                        }
                    }
                }
            ) {
                IconButton(onClick = {
                    if (messageCount > 0) {
                        scope.launch {
                            snackbarHostState.showSnackbar("все сообщения прочитаны")
                            messageCount = 0
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "почта",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                messageCount += Random.nextInt(1, 10)
            }) {
                Text("обновить")
            }
        }
    }
}
```

Все строки интерфейса (кнопки, подписи, сообщения) теперь начинаются со строчной буквы, использован разговорный стиль. Задания чётко разделены на отдельные composable-функции, каждая соответствует одному пункту лабораторной работы.