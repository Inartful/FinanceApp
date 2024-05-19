package com.example.financeapp.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    background = BlackBrown,
    onBackground = Color.White,
    surface = LightSalmon,
    onSurface = Color.White,
    primary = Greenish,
    onPrimary = DarkSalmon,
    secondary = Color.White,
    tertiary = Color.White,
)

private val LightColorScheme = lightColorScheme(

    background = LightSalmon,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    primary = Greenish,
    onPrimary = DarkSalmon,
    secondary = HeadlineGrey,
    tertiary = LittleGrey,
)

@Composable
fun FinanceAppTheme(
    darkTheme: Boolean,
    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}