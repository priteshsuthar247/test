// MainActivity.kt
package com.example.bottomnavapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavapp.ui.theme.BottomNavAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

/**
 * Sealed class to define the navigation routes and their associated icons and titles.
 * This makes it easy to manage navigation items.
 */
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Search : Screen("search", "Search", Icons.Default.Search)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
}

/**
 * Composable function for the main screen of the application.
 * It sets up the navigation controller, bottom navigation bar, and the navigation host.
 */
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Profile
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Search.route) { SearchScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}

/**
 * Composable function for the Home screen.
 * Displays "Home Page" in the center.
 */
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Page", style = MaterialTheme.typography.headlineMedium)
    }
}

/**
 * Composable function for the Search screen.
 * Displays "Search Page" in the center.
 */
@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Search Page", style = MaterialTheme.typography.headlineMedium)
    }
}

/**
 * Composable function for the Profile screen.
 * Displays "Profile Page" in the center.
 */
@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Page", style = MaterialTheme.typography.headlineMedium)
    }
}

/**
 * Preview for the MainScreen composable.
 */
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    BottomNavAppTheme {
        MainScreen()
    }
}

// ui/theme/Theme.kt (Default theme generated by Android Studio, included for completeness)
// package com.example.bottomnavapp.ui.theme
//
// import android.app.Activity
// import android.os.Build
// import androidx.compose.foundation.isSystemInDarkTheme
// import androidx.compose.material3.MaterialTheme
// import androidx.compose.material3.darkColorScheme
// import androidx.compose.material3.dynamicDarkColorScheme
// import androidx.compose.material3.dynamicLightColorScheme
// import androidx.compose.material3.lightColorScheme
// import androidx.compose.runtime.Composable
// import androidx.compose.runtime.SideEffect
// import androidx.compose.ui.graphics.toArgb
// import androidx.compose.ui.platform.LocalContext
// import androidx.compose.ui.platform.LocalView
// import androidx.core.view.WindowCompat
//
// private val DarkColorScheme = darkColorScheme(
//     primary = Purple80,
//     secondary = PurpleGrey80,
//     tertiary = Pink80
// )
//
// private val LightColorScheme = lightColorScheme(
//     primary = Purple40,
//     secondary = PurpleGrey40,
//     tertiary = Pink40
//
//     /* Other default colors to override
//     background = Color(0xFFFFFBFE),
//     surface = Color(0xFFFFFBFE),
//     onPrimary = Color.White,
//     onSecondary = Color.White,
//     onTertiary = Color.White,
//     onBackground = Color(0xFF1C1B1F),
//     onSurface = Color(0xFF1C1B1F),
//     */
// )
//
// @Composable
// fun BottomNavAppTheme(
//     darkTheme: Boolean = isSystemInDarkTheme(),
//     // Dynamic color is available on Android 12+
//     dynamicColor: Boolean = true,
//     content: @Composable () -> Unit
// ) {
//     val colorScheme = when {
//         dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//             val context = LocalContext.current
//             if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//         }
//
//         darkTheme -> DarkColorScheme
//         else -> LightColorScheme
//     }
//     val view = LocalView.current
//     if (!view.isInEditMode) {
//         SideEffect {
//             val window = (view.context as Activity).window
//             window.statusBarColor = colorScheme.primary.toArgb()
//             WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//         }
//     }
//
//     MaterialTheme(
//         colorScheme = colorScheme,
//         typography = Typography,
//         content = content
//     )
// }

// ui/theme/Color.kt (Default colors generated by Android Studio, included for completeness)
// package com.example.bottomnavapp.ui.theme
//
// import androidx.compose.ui.graphics.Color
//
// val Purple80 = Color(0xFFD0BCFF)
// val PurpleGrey80 = Color(0xFFCCC2DC)
// val Pink80 = Color(0xFFEFB8C8)
//
// val Purple40 = Color(0xFF6650a4)
// val PurpleGrey40 = Color(0xFF625b71)
// val Pink40 = Color(0xFF7D5260)

// ui/theme/Type.kt (Default typography generated by Android Studio, included for completeness)
// package com.example.bottomnavapp.ui.theme
//
// import androidx.compose.material3.Typography
// import androidx.compose.ui.text.TextStyle
// import androidx.compose.ui.text.font.FontFamily
// import androidx.compose.ui.text.font.FontWeight
// import androidx.compose.ui.unit.sp
//
// // Set of Material typography styles to start with
// val Typography = Typography(
//     bodyLarge = TextStyle(
//         fontFamily = FontFamily.Default,
//         fontWeight = FontWeight.Normal,
//         fontSize = 16.sp,
//         lineHeight = 24.sp,
//         letterSpacing = 0.5.sp
//     )
//     /* Other default text styles to override
//     titleLarge = TextStyle(
//         fontFamily = FontFamily.Default,
//         fontWeight = FontWeight.Normal,
//         fontSize = 22.sp,
//         lineHeight = 28.sp,
//         letterSpacing = 0.sp
//     ),
//     labelSmall = TextStyle(
//         fontFamily = FontFamily.Default,
//         fontWeight = FontWeight.Medium,
//         fontSize = 11.sp,
//         lineHeight = 16.sp,
//         letterSpacing = 0.5.sp
//     )
//     */
// )
