package com.example.compose
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


@Immutable
data class ColorSheme(
    val primary: Color,
    val containerPrimary: Color,
    val secondary: Color,
    val background: Color,
    val fontColor:Color,
    val errorColor: Color
)


val LocalColorSheme = staticCompositionLocalOf {
    ColorSheme(
        primary = Color.Unspecified,
        containerPrimary = Color.Unspecified,
        secondary = Color.Unspecified,
        fontColor = Color.Unspecified,
        background = Color.Unspecified,
        errorColor = Color.Unspecified
    )
}

val LightTheme =
    ColorSheme(
        primary = primaryLight,
        containerPrimary = containerPrimaryLight,
        secondary = secondaryLight,
        background = backgroundLight,
        errorColor = errorColor,
        fontColor = fontLight
    )

val DarkColorScheme = ColorSheme(
    primary = primaryDark,
    containerPrimary = containerPrimaryDark,
    secondary = secondaryDark,
    background = backgroundDark,
    errorColor = errorColor,
    fontColor = fontDark
)
@Composable
fun RatesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors =
        when {
            darkTheme -> DarkColorScheme
            else -> LightTheme
        }
    CompositionLocalProvider(

        LocalColorSheme  provides colors,
        content = content
    )

}
object RatesTheme {
    val colors: ColorSheme
        @Composable
        get() = LocalColorSheme.current
}