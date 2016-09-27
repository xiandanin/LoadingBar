# LoadingBar

###Screenshot
![Example1](Screenshot/Screenshot.gif)

###Android Studio - 在build.gradle中引入
```java
compile 'com.dyhdyh.loadingbar:loadingbar:1.3'
```
<br/>
####Example
```java
//显示loading(父容器)
//如果传入的parent是getWindow().getDecorView()，Loading就会作用于整个屏幕
LoadingBar.show(parent); 

//显示loading(父容器,自定义样式View,自定义样式View的点击事件)
LoadingBar.show(parent,customView,customViewClickListener); 

//隐藏单个loading(这里的parent就是show方法传入的parent)
LoadingBar.cancel(parent);

//隐藏所有loading
LoadingBar.cancelAll();

```

####Warning
show方法中传的parent，目前仅支持FrameLayout|RelativeLayout|DrawerLayout|CoordinatorLayout|CardView，这样才能覆盖在内容上面。


######Android交流QQ群:146262062
