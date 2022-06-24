package edu.rui.novel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.json.JSONObject
import kotlin.math.roundToInt

//数据库访问，获取最近阅读统计
fun getInfo(): JSONObject {
  val info = JSONObject()
  info.put("hourCount", 34)
  info.put("wordCount", 120000)
  return info
}

//数据库访问，获取书架信息
fun getBooks(): ArrayList<JSONObject> {
  val msgs = ArrayList<JSONObject>()
  for (i in 1..10) {
    val msg1 = JSONObject()
    msg1.put("title", "冥界")
    msg1.put("cover", R.drawable.cover)
    msg1.put("author", "糖糖")
    msg1.put("current", "第300章 女鹅？？")
    msg1.put("latest", "第302章 深海之战，万里奔袭，两肋插刀")
    msgs.add(msg1)
  }
  return msgs
}

//书架
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Books(navController: NavHostController) {
  //左右滑动
  val swipeableState = rememberSwipeableState(0)
  if (swipeableState.offset.value.roundToInt() > 20) {
    navController.navigate("发现")
  }
  //主页面
  LazyColumn(
    modifier = Modifier
      .padding(18.dp, 26.dp, 18.dp, 64.dp)
  ) {
    //最近阅读统计
    item {
      Recent(getInfo())
    }
    //空白
    item {
      Spacer(modifier = Modifier.height(20.dp))
    }
    //书架
    val books = getBooks()
    items(books) { book ->
      BookCard(book,navController)
      //除最后一个外两个item之间添加分割线
      if (book != books.last()) {
        //下横线
        Divider(
          thickness = 1.dp,
          modifier = Modifier.padding(78.dp, 0.dp, 0.dp, 0.dp)
        )
      }
    }
    //底部空白
    item {
      Spacer(modifier = Modifier.height(100.dp))
    }
  }
}

//最近阅读统计
@Composable
fun Recent(info: JSONObject) {
  Row(
    modifier = Modifier
      //裁切圆角
      .clip(shape = RoundedCornerShape(16.dp))
      //背景颜色
      .background(MaterialTheme.colors.onBackground)
      //内边距
      .padding(24.dp, 8.dp)
      .fillMaxWidth()
  ) {
    //最近阅读字数
    Column(modifier = Modifier.weight(0.5f)) {
      Text(
        text = "本月阅读字数:",
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth()
          .padding(0.dp, 2.dp),
      )
      Text(
        text = formatNum(info.getInt("wordCount")) + "字",
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
      )
    }
    
    //最近阅读时长
    Column(modifier = Modifier.weight(0.5f)) {
      Text(
        text = "本月阅读时长:",
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth()
          .padding(0.dp, 2.dp),
      )
      Text(
        text = formatNum(info.getInt("hourCount")) + "小时",
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
      )
    }
  }
}