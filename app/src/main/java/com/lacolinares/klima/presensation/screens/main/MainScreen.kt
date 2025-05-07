package com.lacolinares.klima.presensation.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lacolinares.klima.R
import com.lacolinares.klima.presensation.navigation.BottomNavHost
import com.lacolinares.klima.presensation.navigation.Routes
import com.lacolinares.klima.presensation.theme.BlackPearl
import com.lacolinares.klima.presensation.theme.Mirage
import com.lacolinares.klima.presensation.theme.Neptune
import com.lacolinares.klima.presensation.theme.Silver
import com.lacolinares.klima.presensation.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    userFullName: String,
    onLoadUserFullName: () -> Unit,
    onLogout: () -> Unit
) {
    val bottomNavController = rememberNavController()

    val items = listOf(
        Routes.Main.BottomNavScreen.Home,
        Routes.Main.BottomNavScreen.Weather,
    )
    val selectedIcons = listOf(Icons.Default.Home, Icons.AutoMirrored.Default.List)

    var showLogoutWarning by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        onLoadUserFullName.invoke()
    }

    Scaffold(
        topBar = {
            val greetings = if (userFullName.isNotEmpty()) "Hi $userFullName!" else stringResource(R.string.app_name)
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlackPearl,
                    navigationIconContentColor = Neptune,
                    titleContentColor = Neptune,
                    actionIconContentColor = Neptune
                ),
                title = {
                    Text(text = greetings, fontWeight = FontWeight.Bold)
                },
                actions = {
                    IconButton(onClick = { showLogoutWarning = !showLogoutWarning }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Exit Icon"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = BlackPearl,
                contentColor = Neptune
            ) {
                val currentRoute = bottomNavController.currentBackStackEntryAsState().value?.destination?.route

                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            bottomNavController.navigate(item.route){
                                popUpTo(items.first().route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = selectedIcons[index],
                                contentDescription = null
                            )
                        },
                        label = { Text(text = item.name) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Mirage,
                            selectedTextColor = Neptune,
                            unselectedIconColor = Silver,
                            unselectedTextColor = Silver,
                            indicatorColor = Neptune
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        BottomNavHost(bottomNavController, innerPadding)
    }

    if (showLogoutWarning){
        AlertDialog(
            containerColor = Mirage,
            textContentColor = White,
            text = { Text(text = "Do you want to logout?") },
            onDismissRequest = { showLogoutWarning = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutWarning = false
                        onLogout.invoke()
                    }
                ) {
                    Text("Yes", color = Neptune)
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutWarning = false }) {
                    Text("Cancel", color = Neptune)
                }
            }
        )
    }
}