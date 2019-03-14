# LoadingBar

[English Document](https://github.com/dengyuhan/LoadingBar/blob/master/README-EN.md)
### 示例apk
![](loadingbar-example.png)

## 快速开始
### Android Studio - 在build.gradle中引入
```java
implementation 'com.dyhdyh.loadingbar2:loadingbar:2.0.0'
```
#### LoadingView
```java
//默认样式 loading将会覆盖在parent的内容上面
LoadingBar.view(parent).show();

//自定义样式
LoadingBar.view(parent)
        //.setFactory()
        .setFactoryFromResource(R.layout.layout_custom)
        .show();

//携带参数
LoadingBar.view(parent)
        .extras(new Object[]{})
        .show();

//取消Loading
LoadingBar.view(parent).cancel();
```

#### LoadingDialog
```java
//默认样式
LoadingBar.dialog(context).show();

//自定义样式
LoadingBar.dialog(context)
        //.setFactory()
        .setFactoryFromResource(R.layout.layout_custom)
        .show();
        
//携带参数
LoadingBar.dialog(context)
        .extras(new Object[]{})
        .show();
           
//取消Loading
LoadingBar.dialog(context).cancel();
```

## 自定义Factory
#### ViewFactory
```java
public class CustomViewFactory implements LoadingFactory<ViewGroup, View> {

    @Override
    public View onCreate(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_custom, parent, false);;
    }

    @Override
    public void updateStatus(Object[] extras) {
    	//每次调用show会回调这里  还有携带的参数
    }
}
```
#### DialogFactory
```java
public class CustomDialogFactory implements LoadingFactory<Context, Dialog> {

    @Override
    public Dialog onCreate(Context params) {
        return new AlertDialog.Builder(params)
                .setView(R.layout.layout_custom)
                .create();
    }

    @Override
    public void updateStatus(Object[] extras) {
    	//每次调用show会回调这里  还有携带的参数
    }
}
```
