package com.example.taniapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import com.example.taniapp.R
import com.example.taniapp.ui.components.NavItem
import com.example.taniapp.ui.pages.HomePage
import com.example.taniapp.ui.pages.InventarisProduksiPage
import com.example.taniapp.ui.pages.KeuanganPage
import com.example.taniapp.ui.pages.SettingPage
import com.example.taniapp.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val windowInsetsController = ViewCompat.getWindowInsetsController(view)
        windowInsetsController?.isAppearanceLightStatusBars = false // false untuk ikon putih
    }

    val navItemList = listOf(
        NavItem("Home", R.drawable.icon_home),
        NavItem("Inventaris & Produksi", R.drawable.icon_inventory),
        NavItem("AddTask", R.drawable.icon_home),
        NavItem("Keuangan", R.drawable.icon_wallet),
        NavItem("Settings", R.drawable.icon_setting),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(navItemList[selectedIndex].label, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF55AA68), // Set background color here
                    titleContentColor = Color(0xFFFFFFFF) // Set text color here
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly // Menyebarkan item secara merata
                ) {
                    navItemList.forEachIndexed { index, navItem ->
                        val icon = painterResource(id = navItem.icon)

                        if (navItem.label == "AddTask") {
                            // Custom Button for "AddTask"
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .background(color = Color(0xFF55AA68), shape = RoundedCornerShape(20))
                                    .clickable {
                                        println("Tambah ditekan!")
                                    }
                                    .padding(10.dp) // Padding dalam tombol
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Add,
                                    contentDescription = "Tambah",
                                    tint = Color.White // Ikon warna putih
                                )
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .clickable {
                                        selectedIndex = index
                                    }
                                    .padding(10.dp)
                                    .then(
                                        if (selectedIndex == index) {
                                            Modifier.drawBehind {
                                                val lineHeight = 2.dp.toPx()
                                                val lineWidth = size.width
                                                val lineStart = 0f
                                                val lineOffset = size.height + 8.dp.toPx()

                                                drawRoundRect(
                                                    color = Color(0xFF55AA68),
                                                    topLeft = Offset(lineStart, lineOffset),
                                                    size = Size(lineWidth, lineHeight),
                                                    cornerRadius = CornerRadius.Zero.copy(8.dp.toPx()), // Corner radius untuk membuat rounded ends
                                                )
                                            }
                                        } else Modifier
                                    )
                            ) {
                                val iconColor = if (selectedIndex == index) Color(0xFF55AA68) else Color.Gray

                                Icon(
                                    painter = icon,
                                    contentDescription = navItem.label,
                                    modifier = Modifier.size(24.dp),
                                    tint = iconColor
                                )
                            }
                        }
                    }
                }
            }

//            NavigationBar(
//                containerColor = Color.White
//            ) {
//                navItemList.forEachIndexed { index, navItem ->
//                    val icon = painterResource(id = navItem.icon)
//
//                    if (navItem.label == "AddTask") {
//                        Box(
//                            modifier = Modifier
//                                .padding(4.dp)
//                                .background(color = Color(0xFF55AA68), shape = RoundedCornerShape(20))
//                                .clickable {
//                                    println("Tambah ditekan!")
//                                }
//                                .padding(10.dp) // Padding dalam tombol
//                        ) {
//                            Icon(
//                                imageVector = Icons.Rounded.Add,
//                                contentDescription = "Tambah",
//                                tint = Color.White // Ikon warna putih
//                            )
//                        }
//                    } else {
//                        NavigationBarItem(
//                            selected = selectedIndex == index,
//                            onClick = {
//                                selectedIndex = index
//                            },
//                            icon = {
//                                Icon(painter = icon, contentDescription = navItem.label, modifier = Modifier.size(24.dp))
//                            },
//                        )
//                    }
//
//                }
//            }
        }
    ) {
            innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex, navController)
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int, navController: NavController) {
    when (selectedIndex) {
        0 -> HomePage(modifier = modifier, navController = navController)
        1 -> InventarisProduksiPage(modifier = modifier, navController = navController)
        2 -> HomePage(modifier = modifier, navController = navController)
        3 -> KeuanganPage(modifier = modifier, navController = navController)
        4 -> SettingPage(modifier = modifier, navController = navController)
    }
}
