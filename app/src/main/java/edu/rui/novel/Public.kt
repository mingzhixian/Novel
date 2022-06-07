package edu.rui.novel

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.json.JSONObject

//每本书的卡片
@Composable
fun BookCard(msg: JSONObject) {
  //横向排列
  Row(
    modifier = Modifier
      //高度
      .height(120.dp)
      .clickable { toBook(msg) },
    verticalAlignment = Alignment.CenterVertically
  ) {
    //封面
    Image(
      //封面图片
      painter = painterResource(id = msg.getInt("cover")),
      //描述
      contentDescription = "封面",
      modifier = Modifier
        //大小
        .height(120.dp)
        .width(60.dp)
        .clip(RoundedCornerShape(12.dp)),
      alignment = Alignment.Center,
    )
    //文字
    Column(modifier = Modifier
      .padding(14.dp, 0.dp, 0.dp, 0.dp)
      .fillMaxWidth()) {
      //标题
      Text(
        text = msg.getString("title"),
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
      )
      Text(
        text = msg.getString("author"),
        color = MaterialTheme.colors.secondary,
        fontSize = 12.sp,
        modifier = Modifier
          .padding(0.dp, 4.dp)
      )
      if (msg.has("introduction")) {
        //详情
        Text(
          text = msg.getString("introduction"),
          color = MaterialTheme.colors.primary,
          overflow = TextOverflow.Ellipsis,
          fontSize = 14.sp,
          maxLines = 2,
        )
      } else {
        //当前章节
        Text(
          text = msg.getString("current"),
          color = MaterialTheme.colors.primary,
          overflow = TextOverflow.Ellipsis,
          fontSize = 14.sp,
          maxLines = 1,
        )
        //最新章节
        Text(
          text = msg.getString("latest"),
          color = MaterialTheme.colors.primary,
          overflow = TextOverflow.Ellipsis,
          fontSize = 14.sp,
          maxLines = 1,
        )
      }
    }
  }
}

//进入书本界面(详情界面或阅读界面)
fun toBook(msg: JSONObject) {
}

//大数字格式化为千、万单位
fun formatNum(num: Int): String {
  //千万
  if (num >= 10000000) {
    return String.format("%.1f", (num.toFloat() / 10000000.0)) + "千万"
  }
  //百万
  if (num >= 1000000) {
    return String.format("%.1f", (num.toFloat() / 1000000.0)) + "百万"
  }
  //万
  if (num >= 10000) {
    return String.format("%.1f", (num.toFloat() / 10000.0)) + "万"
  }
  //千
  if (num >= 1000) {
    return String.format("%.1f", (num.toFloat() / 1000.0)) + "千"
  }
  return num.toString()
}

//上下导航栏
@Composable
fun Bars(navController: NavHostController) {
  //页面名称
  val items = listOf("书架", "发现", "我的")
  //页面图标
  val itemsIcon = listOf(R.drawable.books, R.drawable.find, R.drawable.me)
  //页面被选中时图标
  val itemsIconSelect = listOf(R.drawable.books_selected, R.drawable.find_selected, R.drawable.me_selected)
  //当前选中页面
  var selectedItem by remember { mutableStateOf(0) }
  Scaffold(
    //上方标题栏
    topBar = {
      TopAppBar(
        //背景颜色
        backgroundColor = MaterialTheme.colors.background,
      ) {
        //标题
        Text(
          text = items[selectedItem],
          //字体大小
          fontSize = 24.sp,
          //粗细
          fontWeight = FontWeight.Bold,
          //颜色
          color = MaterialTheme.colors.primary,
          modifier = Modifier.padding(18.dp, 0.dp),
        )
        //搜索(我的界面没有)
        if (items[selectedItem] != "我的") {
          Image(
            painter = painterResource(id = R.drawable.search), contentDescription = "搜索",
            modifier = Modifier
              .size(22.dp)
              .padding(18.dp, 0.dp),
            //内部图片对齐方式
            alignment = Alignment.Center,
          )
        }
      }
    },
    //底部导航栏
    bottomBar = {
      BottomAppBar(
        modifier = Modifier
          //高度
          .height(54.dp),
        //背景颜色
        backgroundColor = MaterialTheme.colors.background,
      ) {
        items.forEachIndexed { index, item ->
          BottomNavigationItem(
            icon = {
              Image(
                painter = painterResource(id = if (selectedItem == index) itemsIconSelect[index] else itemsIcon[index]),
                contentDescription = "图标",
                modifier = Modifier
                  .size(34.dp),
                //内部图片对齐方式
                alignment = Alignment.Center,
              )
            },
            selected = selectedItem == index,
            onClick = {
              selectedItem = index
              navController.navigate(item)
            }
          )
        }
      }
    }
  ) {
    //路由图
    NavHost(
      navController = navController,
      startDestination = "书架",
    ) {
      composable("书架") {
        Books(navController = navController)
      }
      composable("发现") {
        Find(navController = navController)
      }
      composable("我的") {
        Me(navController = navController)
      }
    }
  }
}
