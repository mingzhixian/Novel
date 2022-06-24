package edu.rui.novel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Me(navController: NavHostController) {
  LazyColumn(modifier = Modifier.padding(18.dp, 16.dp)) {
    item {
      Column(
        modifier = Modifier
          .clip(shape = RoundedCornerShape(16.dp))
          .background(MaterialTheme.colors.onBackground)
          .fillMaxWidth()
          .padding(18.dp, 18.dp)
      ) {
          Text(
            text = "黑色主题",
            color = MaterialTheme.colors.primary,
            fontSize = 16.sp,
            modifier = Modifier
              .padding(0.dp, 4.dp)
          )
        
        Text(text = "主题设置", color = MaterialTheme.colors.primary, fontSize = 16.sp, modifier = Modifier
          .padding(0.dp, 4.dp)
          .clickable { navController.navigate("主题设置") })
      }
    }
    item {
      Spacer(modifier = Modifier.height(20.dp))
    }
    item {
      Column(
        modifier = Modifier
          .clip(shape = RoundedCornerShape(16.dp))
          .background(MaterialTheme.colors.onBackground)
          .fillMaxWidth()
          .padding(18.dp, 18.dp)
      ) {
        Text(text = "捐赠", color = MaterialTheme.colors.primary, fontSize = 16.sp, modifier = Modifier.padding(0.dp, 4.dp))
        Text(text = "反馈", color = MaterialTheme.colors.primary, fontSize = 16.sp, modifier = Modifier.padding(0.dp, 4.dp))
        Text(text = "关于阅", color = MaterialTheme.colors.primary, fontSize = 16.sp, modifier = Modifier.padding(0.dp, 4.dp))
      }
    }
  }
}