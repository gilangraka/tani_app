package com.example.taniapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.taniapp.R
import com.example.taniapp.viewmodel.AuthState
import com.example.taniapp.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel) {
//    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var termsChecked by remember { mutableStateOf(false) }

    val authState = authViewModel.authState.observeAsState()
    val context =  LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Back")
        }
        Text(
            text = "Create account",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "Fix your information below or register with your social account",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )

//        // Name field
//        OutlinedTextField(
//            value = name,
//            onValueChange = { name = it },
//            label = { Text("Name") },
//            modifier = Modifier.fillMaxWidth(),
//            singleLine = true
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))

        // Email field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                    val icon = if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
//                    Icon(painter = painterResource(id = icon), contentDescription = null)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Terms and Conditions checkbox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = termsChecked,
                onCheckedChange = { termsChecked = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFFFF9800))
            )
            Spacer(modifier = Modifier.width(8.dp))
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("Agree with ")
                    }
                    pushStringAnnotation(tag = "URL", annotation = "https://example.com/terms")
                    withStyle(style = SpanStyle(color = Color(0xFFFF9800), textDecoration = TextDecoration.Underline)) {
                        append("Terms & Condition")
                    }
                    pop()
                },
                onClick = { /* Handle Terms & Condition link click */ }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = { authViewModel.signup(email, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF4CAF50)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(text = "Daftar", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
            Text(
                text = " Or sign with ",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            HorizontalDivider(modifier = Modifier.weight(1f), thickness = 1.dp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
//            IconButton(onClick = { /* Handle TikTok sign in */ }) {
//                Image(painter = painterResource(id = R.drawable.ic_tiktok), contentDescription = "TikTok")
//            }
            IconButton(onClick = { /* Handle Google sign in */ }) {
                Image(painter = painterResource(id = R.drawable.ic_google), contentDescription = "Google")
            }
//            IconButton(onClick = { /* Handle Facebook sign in */ }) {
//                Image(painter = painterResource(id = R.drawable.ic_fb), contentDescription = "Facebook")
//            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sign In Text
        Row {
            Text(text = "Already have an account?", color = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            ClickableText(
                text = AnnotatedString("Sign In"),
                onClick = { navController.navigate("signin") },
                style = TextStyle(color = Color(0xFFFF9800), textDecoration = TextDecoration.Underline)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    RegisterScreen(navController = navController, authViewModel = authViewModel)
}