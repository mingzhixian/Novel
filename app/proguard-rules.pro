#指定代码的压缩级别
-optimizationpasses 5

#包名不混合大小写
-dontusemixedcaseclassnames

#优化 不优化输入的类文件
-dontoptimize

#混淆时是否记录日志
-verbose

#忽略警告
-ignorewarnings

#保护注解
-keepattributes *Annotation*

-keep public class * extends android.app.Application
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class * extends edu.rui.novel.*

-keepclasseswithmembernames class * {
  native <methods>;
}
-keepclassmembers class * extends android.app.Activity {
  public void *(android.view.View);
}
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
 public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class **.R$* {
  *;
}
-keep class * extends android.view.View{*;}
-keep class * extends android.app.Dialog{*;}
-keep class * implements java.io.Serializable{*;}

#pulltorefresh
-keep class com.handmark.pulltorefresh.** { *; }
-keep class android.support.v4.** { *;}
-keep public class * extends android.support.v4.**{
 public protected *;}
-keep class android.support.v7.** {*;}