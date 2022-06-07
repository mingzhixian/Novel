package edu.rui.novel.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import edu.rui.novel.screen.Bars
import edu.rui.novel.activity.ui.theme.NovelTheme

//初始化
class Index : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      NovelTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          val navController = rememberNavController()
          Bars(navController = navController)
        }
      }
    }
  }
}

//预览
@Preview(
  name = "Dark Mode",
  uiMode = Configuration.UI_MODE_NIGHT_YES,
  showBackground = true
)
@Preview(
  name = "Light Mode",
  uiMode = Configuration.UI_MODE_NIGHT_NO,
  showBackground = true
)
@Composable
fun DefaultPreview() {
  NovelTheme {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colors.background
    ) {
      val navController = rememberNavController()
      Bars(navController = navController)
    }
  }
}
