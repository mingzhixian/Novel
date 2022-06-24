package edu.rui.novel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.json.JSONObject

@Composable
fun Find(navController:NavHostController) {
  //选中的分类
  var selectSort by remember { mutableStateOf(0) }
  //选中的附加信息
  var selectCountry by remember { mutableStateOf(0) }
  var selectStatus by remember { mutableStateOf(0) }
  //主页面
  Column(modifier = Modifier.padding(18.dp, 10.dp,18.dp,64.dp)) {
    Column(
      modifier = Modifier
        .clip(shape = RoundedCornerShape(16.dp))
        .background(MaterialTheme.colors.onBackground)
        .fillMaxWidth()
        .padding(8.dp, 8.dp)
    ) {
      //国家
      val countrys = getCountrys()
      Row {
        Text(
          text = "国家地区:",
          color = MaterialTheme.colors.primary,
          fontSize = 14.sp,
          textAlign = TextAlign.Center,
          modifier = Modifier
            .padding(6.dp, 8.dp)
        )
        LazyRow {
          itemsIndexed(countrys) { index, country ->
            Text(
              text = country.getString("name"),
              color = MaterialTheme.colors.primary,
              fontSize = 14.sp,
              textAlign = TextAlign.Center,
              modifier = Modifier
                .clip(shape = RoundedCornerShape(18.dp))
                .background(if (selectCountry == index) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.onBackground)
                .padding(6.dp, 8.dp)
                .clickable { selectCountry=index },
            )
          }
        }
      }
      Spacer(modifier = Modifier.height(6.dp))
      //连载状态
      Row {
        Text(
          text = "连载状态:",
          color = MaterialTheme.colors.primary,
          fontSize = 14.sp,
          textAlign = TextAlign.Center,
          modifier = Modifier
            .padding(6.dp, 6.dp)
        )
        LazyRow {
          val status = arrayListOf("连载中", "完结")
          itemsIndexed(status) { index, stat ->
            Text(
              text = stat,
              color = MaterialTheme.colors.primary,
              fontSize = 14.sp,
              textAlign = TextAlign.Center,
              modifier = Modifier
                .clip(shape = RoundedCornerShape(18.dp))
                .background(if (selectStatus == index) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.onBackground)
                .padding(6.dp, 8.dp)
                .clickable { selectStatus=index },
            )
          }
        }
      }
    }
    Spacer(modifier = Modifier.height(12.dp))
    //下栏
    Row {
      //所有分类
      val sorts = getSorts()
      //所有分类
      LazyColumn(
        modifier = Modifier
          .clip(shape = RoundedCornerShape(16.dp))
          //宽度
          .width(100.dp)
          //撑起全部高度
          .fillMaxHeight()
          //背景颜色
          .background(MaterialTheme.colors.onBackground)
          //内边距
          .padding(8.dp, 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        itemsIndexed(sorts) { index, sort ->
          Text(
            text = sort.getString("name"),
            color = MaterialTheme.colors.primary,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
              .clip(shape = RoundedCornerShape(18.dp))
              .fillMaxWidth()
              .background(if (selectSort == index) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.onBackground)
              .padding(0.dp, 8.dp)
              .clickable { selectSort = index },
          )
          
        }
      }
      //分类下图书排行榜
      LazyColumn(
        modifier = Modifier
          .fillMaxHeight()
          .padding(8.dp, 0.dp, 0.dp, 0.dp),
      ) {
        //列表
        val books = getSortBooks(sorts[selectSort])
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
  }
  
}

//联网获取所有国家分类
fun getCountrys(): ArrayList<JSONObject> {
  val countrys = ArrayList<JSONObject>()
  countrys.add(JSONObject())
  countrys.add(JSONObject())
  countrys.add(JSONObject())
  countrys.add(JSONObject())
  countrys[0].put("name", "中国大陆")
  countrys[1].put("name", "日本")
  countrys[2].put("name", "欧美")
  countrys[3].put("name", "韩国")
  return countrys
}

//联网获取所有分类统计
fun getSorts(): ArrayList<JSONObject> {
  val sorts = ArrayList<JSONObject>()
  for (i in 1..10) {
    val sort = JSONObject()
    if (i % 2 == 0) {
      sort.put("name", "玄幻小说")
    } else {
      sort.put("name", "历史小说")
    }
    sorts.add(sort)
  }
  return sorts
}

//联网获取该类书籍信息
fun getSortBooks(sort: JSONObject): ArrayList<JSONObject> {
  val msgs = ArrayList<JSONObject>()
  if (sort.getString("name") == "玄幻小说") {
    for (i in 1..10) {
      val msg1 = JSONObject()
      msg1.put("title", "冥界")
      msg1.put("cover", R.drawable.cover)
      msg1.put("author", "糖糖")
      msg1.put("introduction", "一边是高冷女神，一边是霸道御姐。两个同样身世成谜，水火不容的女人让他左右为难。而因为他引发的争端缓缓展开，一步一步走向更深层次的秘密……")
      msgs.add(msg1)
    }
  } else if (sort.getString("name") == "历史小说") {
    for (i in 1..10) {
      val msg1 = JSONObject()
      msg1.put("title", "明史")
      msg1.put("cover", R.drawable.cover)
      msg1.put("author", "糖不甜")
      msg1.put("introduction", "全球灾变后六十年，小冰冻期结束，各生活大区政府开始大规模收拢待规划无政府区的土地，重整资源，全面进入了复苏阶段，而这二十年也被称为“黄金二十年”。    这是一个新大区陆续崛起，政治搭台，资本唱戏，野心家遍地走，英雄豪强，奸雄草根并起的璀璨大时代！一位青年带着满腹韬略崛起于乱世，胸藏猛虎，丈量天地。")
      msgs.add(msg1)
    }
    
  }
  return msgs
}
