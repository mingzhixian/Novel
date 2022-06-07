package edu.rui.novel

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Me(navController: NavHostController) {
  Text(
    text = "这里是我的",
    fontSize = 30.sp
  )
}