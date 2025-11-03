package com.dennnisver4.habithub

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                // Creamos la transición y las animaciones aquí para poder sincronizar la gráfica y el texto
                val transition = rememberInfiniteTransition()
                val anims =
                    List(6) { index ->
                        transition.animateFloat(
                            initialValue = 0f,
                            targetValue = 1f,
                            animationSpec =
                                infiniteRepeatable(
                                    animation =
                                        keyframes {
                                            durationMillis = 1200
                                            0f at 0
                                            1f at 600
                                            0.2f at 1000
                                        },
                                    repeatMode = RepeatMode.Reverse,
                                    initialStartOffset = StartOffset(index * 120),
                                ),
                        )
                    }

                // Promedio de los valores animados para desplazar el texto en sincronía
                val avg = anims.map { it.value }.average().toFloat()

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AnimatedLineChart(anims = anims, modifier = Modifier.size(220.dp))
                    Spacer(modifier = Modifier.height(20.dp))
                    // Texto sincronizado con la gráfica: se desplaza verticalmente según el promedio
                    Text(
                        text = stringResource(id = R.string.habit_tracking),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF81D4FA), // azul claro
                        modifier = Modifier.offset(y = ((0.5f - avg) * 40f).dp),
                    )
                }
                LaunchedEffect(Unit) {
                    // Mostrar el splash ~1.8s y luego abrir MainActivity
                    delay(1800)
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }
}

@Composable
fun AnimatedLineChart(
    anims: List<State<Float>>,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val spacing = if (anims.size > 1) w / (anims.size - 1) else w

        val points =
            anims.mapIndexed { i, state ->
                val x = i * spacing
                // y se mueve en torno a la mitad del canvas; multiplicador controla amplitud
                val baseY = h * 0.6f
                val amplitude = h * 0.45f
                val y = baseY - (state.value * amplitude - amplitude / 2f)
                Offset(x, y)
            }

        // Línea principal
        val path =
            Path().apply {
                if (points.isNotEmpty()) moveTo(points.first().x, points.first().y)
                for (p in points.drop(1)) lineTo(p.x, p.y)
            }

        drawPath(path, color = Color(0xFF4CAF50), style = Stroke(width = 8f, cap = StrokeCap.Round))

        // Marcar puntos
        points.forEach { drawCircle(Color.White, radius = 6f, center = it) }
    }
}
