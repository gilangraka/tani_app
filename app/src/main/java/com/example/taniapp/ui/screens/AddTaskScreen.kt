package com.example.taniapp.ui.screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.example.taniapp.ui.components.ButtonText
import com.example.taniapp.ui.components.CustomText
import com.example.taniapp.ui.components.SearchTextField
import java.util.Calendar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(modifier: Modifier = Modifier, navController: NavController) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val windowInsetsController = ViewCompat.getWindowInsetsController(view)
        windowInsetsController?.isAppearanceLightStatusBars = false // false untuk ikon putih
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("New Task", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF55AA68),
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali", tint = Color.White)
                    }
                }
            )
        }
    ) {
        innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContentScreen(modifier: Modifier) {
    val category = listOf(
        "Perencanaan panen", "Project", "Today",
        "Penjadwalan", "Manajemen stok", "Penyimpanan",
        "Hasil panen", "Lainnya"
    )

    var task_name by remember { mutableStateOf("") }
    var task_description by remember { mutableStateOf("") }
    val selectedCategory = remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            CustomText("Task Name")
            SearchTextField(
                text = "Create task management",
                query = task_name,
                onQueryChange = {task_name = it}
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            CustomText("Description")
            SearchTextField(
                text = "Optional, max 10 characters",
                query = task_description,
                onQueryChange = {task_description = it}
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            CustomText("Category")
            FlowRow {
                category.forEach { item ->
                    val isSelected = selectedCategory.value == item
                    Button(
                        onClick = { selectedCategory.value = item },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Color(0xFF55AA68) else Color.LightGray
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(4.dp)
                    ) {
                        CustomText(item, color = if (isSelected) Color.White else Color.Black)
                    }
                }
            }
        }

        ButtonText(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Add Task"
        )
    }
}