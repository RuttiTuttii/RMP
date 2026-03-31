package com.example.fragmentzalanimationlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

// ====================== ГЛАВНЫЙ АКТИВИТИ ======================
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FragmentzalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    AnimationLabScreen()
                }
            }
        }
    }
}

// ====================== ТЕМА (glassmorphism как в вашем веб-проекте) ======================
@Composable
fun FragmentzalTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Color(0xFF0A0A0A),
            surface = Color(0xFF171717).copy(alpha = 0.4f),
            primary = Color(0xFFFAFAFA),
            onPrimary = Color(0xFF0A0A0A),
            secondary = Color(0xFF262626).copy(alpha = 0.6f),
            onSecondary = Color(0xFFFAFAFA),
            outline = Color(0xFFFFFFFF).copy(alpha = 0.1f)
        ),
        typography = Typography(
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = FontFamily.Monospace // Fira Mono как в вашем проекте
            )
        )
    ) {
        content()
    }
}

// ====================== GLASSCARD (точно как в вашем GlassCard.tsx) ======================
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = Color(0xFF171717).copy(alpha = 0.4f),
                shape = RoundedCornerShape(28.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFFFFFFF).copy(alpha = 0.1f),
                shape = RoundedCornerShape(28.dp)
            )
            .shadow(
                elevation = 40.dp,
                shape = RoundedCornerShape(28.dp),
                spotColor = Color.Black.copy(alpha = 0.3f)
            )
            .padding(1.dp)
    ) {
        // Топ-подсветка как в вашем веб-проекте
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = 0.06f),
                            Color.Transparent
                        )
                    )
                )
        )
        content()
    }
}

// ====================== ОСНОВНОЙ ЭКРАН ======================
@Composable
fun AnimationLabScreen() {
    val infiniteTransition = rememberInfiniteTransition(label = "ambient")

    // Анимированные ambient blobs (точно как в App.tsx)
    val blob1X = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val blob2Y = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(25000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Фон с ambient blobs
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0A0A0A))
        ) {
            // Blob 1
            Box(
                modifier = Modifier
                    .size(350.dp)
                    .offset(
                        x = (blob1X.value * 120 - 60).dp,
                        y = (-40 + blob1X.value * 80).dp
                    )
                    .blur(120.dp)
                    .background(Color.White.copy(alpha = 0.04f), CircleShape)
                    .align(Alignment.TopStart)
            )
            // Blob 2
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .offset(
                        x = (-50 + blob2Y.value * 100).dp,
                        y = (50 + blob2Y.value * 80 - 40).dp
                    )
                    .blur(100.dp)
                    .background(Color.White.copy(alpha = 0.03f), CircleShape)
                    .align(Alignment.BottomEnd)
            )
            // Grid overlay (как в вашем проекте)
            Canvas(modifier = Modifier.fillMaxSize()) {
                val gridSize = 80f
                for (x in 0..size.width.toInt() step gridSize.toInt()) {
                    drawLine(
                        color = Color.White.copy(alpha = 0.07f),
                        start = Offset(x.toFloat(), 0f),
                        end = Offset(x.toFloat(), size.height),
                        strokeWidth = 1f
                    )
                }
                for (y in 0..size.height.toInt() step gridSize.toInt()) {
                    drawLine(
                        color = Color.White.copy(alpha = 0.07f),
                        start = Offset(0f, y.toFloat()),
                        end = Offset(size.width, y.toFloat()),
                        strokeWidth = 1f
                    )
                }
            }
        }

        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = { BottomNav() },
            topBar = {
                // Header как в вашем Header.tsx
                GlassCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "fragmentzal",
                            fontSize = 22.sp,
                            fontFamily = FontFamily.Monospace,
                            color = Color.White
                        )
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Theme",
                            tint = Color.White
                        )
                    }
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Лабораторная работа №24\nНастройка анимации (Jetpack Compose)",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White
                )

                // 5.1 Animate*AsState
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    AnimationSection5_1()
                }

                // 5.2 Animatable
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    AnimationSection5_2()
                }

                // 5.3 UpdateTransition
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    AnimationSection5_3()
                }

                // 5.4 InfiniteTransition
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    AnimationSection5_4()
                }

                // 5.5 AnimatedVisibility
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    AnimationSection5_5()
                }
            }
        }
    }
}

// ====================== BOTTOM NAV (как в вашем BottomNav.tsx) ======================
@Composable
fun BottomNav() {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf(
                Icons.Default.Home to "Главная",
                Icons.Default.CreditCard to "Тарифы",
                Icons.Default.MenuBook to "Гайд",
                Icons.Default.Person to "Профиль",
                Icons.Default.Dashboard to "Панель"
            ).forEach { (icon, label) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(icon, contentDescription = label, tint = Color.White)
                    Text(label, fontSize = 10.sp, fontFamily = FontFamily.Monospace, color = Color.White.copy(alpha = 0.7f))
                }
            }
        }
    }
}

// ====================== 5.1 Анимация с Animate*AsState ======================
@Composable
fun AnimationSection5_1() {
    var colorState by remember { mutableStateOf(Color(0xFFEF4444)) }
    val animatedColorTween = animateColorAsState(
        targetValue = colorState,
        animationSpec = tween(durationMillis = 3000),
        label = "tween"
    )

    val animatedColorKeyframes = animateColorAsState(
        targetValue = colorState,
        animationSpec = keyframes {
            durationMillis = 10000
            Color(0xFFEF4444) at 0
            Color(0xFF22C55E) at 2500
            Color(0xFF3B82F6) at 5000
            Color(0xFFA855F7) at 7500
            Color(0xFFEF4444) at 10000
        },
        label = "keyframes"
    )

    var squareSize by remember { mutableStateOf(100.dp) }
    val animatedSquareSizeRepeat = animateDpAsState(
        targetValue = squareSize,
        animationSpec = repeatable(
            iterations = 3,
            animation = tween(800)
        ),
        label = "repeatable"
    )

    val animatedSquareSizeSpring = animateDpAsState(
        targetValue = squareSize,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "spring"
    )

    val infiniteColor = animateColorAsState(
        targetValue = if (System.currentTimeMillis() % 2000 < 1000) Color(0xFF06B6D4) else Color(0xFF8B5CF6),
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "infinite"
    )

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("5.1 Animate*AsState", style = MaterialTheme.typography.titleMedium, color = Color.White)

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(animatedColorTween.value, RoundedCornerShape(12.dp))
                    .clickable { colorState = if (colorState == Color(0xFFEF4444)) Color(0xFF22C55E) else Color(0xFFEF4444) }
            )
            Text("Цвет 3 сек (tween)", color = Color.White, fontSize = 14.sp)
        }

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(animatedColorKeyframes.value, RoundedCornerShape(12.dp))
                    .clickable { colorState = if (colorState == Color(0xFFEF4444)) Color(0xFF22C55E) else Color(0xFFEF4444) }
            )
            Text("Цвет 10 сек + 3 цвета (keyframes)", color = Color.White, fontSize = 14.sp)
        }

        // Квадратная картинка (добавьте square_image.png в res/drawable)
        val squarePainter: Painter = painterResource(id = R.drawable.square_image) // ← добавьте изображение
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = squarePainter,
                contentDescription = "Square",
                modifier = Modifier
                    .size(animatedSquareSizeRepeat.value)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { squareSize = if (squareSize == 100.dp) 180.dp else 100.dp }
            )
            Spacer(Modifier.width(12.dp))
            Text("Размер квадрата ×3 (repeatable)", color = Color.White)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = squarePainter,
                contentDescription = "Square",
                modifier = Modifier
                    .size(animatedSquareSizeSpring.value)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { squareSize = if (squareSize == 100.dp) 180.dp else 100.dp }
            )
            Spacer(Modifier.width(12.dp))
            Text("Размер квадрата (spring)", color = Color.White)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(infiniteColor.value, RoundedCornerShape(16.dp))
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Бесконечная смена цвета контейнера (infiniteRepeatable)", color = Color.White, textAlign = TextAlign.Center)
        }
    }
}

// ====================== 5.2 Animatable ======================
@Composable
fun AnimationSection5_2() {
    val colorAnimatable = remember { Animatable(Color(0xFFEF4444)) }
    val scope = rememberCoroutineScope()

    val sizeAnimatable = remember { Animatable(100f) }
    val rotationAnimatable = remember { Animatable(0f) }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("5.2 Animatable", style = MaterialTheme.typography.titleMedium, color = Color.White)

        Box(
            modifier = Modifier
                .size(80.dp)
                .background(colorAnimatable.value, CircleShape)
                .clickable {
                    scope.launch {
                        colorAnimatable.animateTo(if (colorAnimatable.value == Color(0xFFEF4444)) Color(0xFF22C55E) else Color(0xFFEF4444))
                    }
                }
        )
        Text("Смена цвета через Animatable", color = Color.White)

        // Круглая картинка (добавьте round_image.png)
        val roundPainter: Painter = painterResource(id = R.drawable.round_image)
        Box(
            modifier = Modifier
                .size(sizeAnimatable.value.dp)
                .clip(CircleShape)
                .clickable {
                    scope.launch {
                        sizeAnimatable.animateTo(
                            targetValue = if (sizeAnimatable.value == 100f) 180f else 100f,
                            animationSpec = repeatable(3, tween(600))
                        )
                    }
                }
        ) {
            Image(painter = roundPainter, contentDescription = "Round", modifier = Modifier.fillMaxSize())
        }
        Text("Размер круглой картинки ×3 (repeatable)", color = Color.White)

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .rotate(rotationAnimatable.value)
                .clickable {
                    scope.launch {
                        rotationAnimatable.animateTo(
                            targetValue = rotationAnimatable.value + 360f,
                            animationSpec = tween(2000)
                        )
                    }
                }
        ) {
            Image(painter = roundPainter, contentDescription = "Round rotate", modifier = Modifier.fillMaxSize())
        }
        Text("Поворот картинки 360° за 2 сек", color = Color.White)
    }
}

// ====================== 5.3 UpdateTransition ======================
@Composable
fun AnimationSection5_3() {
    var isLarge by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isLarge, label = "size+rotate")

    val sizeWidth by transition.animateDp(label = "width") { if (it) 300.dp else 100.dp }
    val sizeHeight by transition.animateDp(label = "height") { if (it) 600.dp else 200.dp }
    val rotation by transition.animateFloat(label = "rotation") { if (it) 360f else 0f }

    val squarePainter: Painter = painterResource(id = R.drawable.square_image)

    Column(modifier = Modifier.padding(16.dp)) {
        Text("5.3 UpdateTransition", style = MaterialTheme.typography.titleMedium, color = Color.White)

        Image(
            painter = squarePainter,
            contentDescription = "Transition image",
            modifier = Modifier
                .size(width = sizeWidth, height = sizeHeight)
                .rotate(rotation)
                .clip(RoundedCornerShape(12.dp))
                .clickable { isLarge = !isLarge }
                .border(2.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = if (isLarge) "300×600 + поворот 360°" else "100×200\n(нажмите для переключения)",
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

// ====================== 5.4 InfiniteTransition (FAB) ======================
@Composable
fun AnimationSection5_4() {
    val infiniteTransition = rememberInfiniteTransition(label = "fab")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("5.4 InfiniteTransition — FloatingActionButton", style = MaterialTheme.typography.titleMedium, color = Color.White)

        FloatingActionButton(
            onClick = {},
            containerColor = Color(0xFF06B6D4),
            modifier = Modifier.scale(scale)
        ) {
            Icon(Icons.Default.Phone, contentDescription = "Звонок", tint = Color.White)
        }
        Text("Бесконечное изменение размера FAB", color = Color.White, modifier = Modifier.padding(top = 8.dp))
    }
}

// ====================== 5.5 AnimatedVisibility ======================
@Composable
fun AnimationSection5_5() {
    var menuVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("5.5 AnimatedVisibility — меню бургер", style = MaterialTheme.typography.titleMedium, color = Color.White)

        Button(
            onClick = { menuVisible = !menuVisible },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f))
        ) {
            Icon(Icons.Default.Menu, contentDescription = "Бургер")
            Spacer(Modifier.width(8.dp))
            Text("Открыть меню")
        }

        AnimatedVisibility(
            visible = menuVisible,
            enter = fadeIn() + slideInVertically(initialOffsetY = { -40 }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { -40 })
        ) {
            GlassCard(modifier = Modifier.padding(top = 8.dp)) {
                Column {
                    Text("Настройки", modifier = Modifier.padding(16.dp), color = Color.White)
                }
            }
        }

        AnimatedVisibility(
            visible = menuVisible,
            enter = fadeIn(animationSpec = tween(400)) + scaleIn(initialScale = 0.8f),
            exit = fadeOut() + scaleOut(targetScale = 0.8f)
        ) {
            GlassCard(modifier = Modifier.padding(top = 8.dp)) {
                Column {
                    Text("Контакты", modifier = Modifier.padding(16.dp), color = Color.White)
                }
            }
        }

        AnimatedVisibility(
            visible = menuVisible,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            GlassCard(modifier = Modifier.padding(top = 8.dp)) {
                Column {
                    Text("О программе", modifier = Modifier.padding(16.dp), color = Color.White)
                }
            }
        }
    }
}