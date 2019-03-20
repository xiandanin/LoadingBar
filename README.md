# LoadingBar

[English Document](README-EN.md)

LoadingBar已升级`2.0`，[1.x](https://github.com/dengyuhan/LoadingBar/tree/1.x)版本无法迁移2.0，但[1.x](https://github.com/dengyuhan/LoadingBar/tree/1.x)会持续维护

## 下载示例
<img src="screenshots/download.png" width="100"/>

## Gralde引入
```java
implementation 'com.dyhdyh.loadingbar2:loadingbar:2.0.0'
```

## 简单用法（默认样式）
#### LoadingView
```
//以View的形式显示loading
//这里的parent需要是可覆盖的布局，如果传的不是可覆盖的布局，会一直随着层级往上找
//FrameLayout/RelativeLayout/ConstraintLayout/DrawerLayout/CoordinatorLayout/CardView
LoadingBar.view(parent).show();

//需要跟show方法传的是同一个parent 才能正确取消
LoadingBar.view(parent).cancel();
```

#### LoadingDialog
```
//以Dialog的形式显示Loading
//注意这里context不能用ApplicationContext
LoadingBar.dialog(context).show();

//取消Dialog
//需要跟show方法传的是同一个context 才能正确取消
LoadingBar.dialog(context).cancel();
```

## 进阶用法
#### LoadingView

```
LoadingBar.view(parent)
        //通过工厂设置样式
        //.setFactory(new CustomViewFactory())
        //通过View设置样式
        //.setFactoryFromView(view)
        //通过布局ID设置样式
        .setFactoryFromResource(R.layout.layout_custom)
        //携带参数
        .extras(new Object[]{})
        .show();

//通过工厂设置样式
public class CustomViewFactory implements LoadingFactory<ViewGroup, View> {

    /**
     * 工厂类的标识符
     * 在cancel()之前多次调用show()时，当key相同时不会重新调用onCreate
     * @return
     */
    @Override
    public String getKey() {
        return getClass().getName();
    }

    @Override
    public View onCreate(ViewGroup parent) {
        //这里return的View就是Loading的样式
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_custom, parent, false);;
    }

    @Override
    public void updateStatus(Object[] extras) {
    	//每次调用show会回调这里  还有携带的参数
    }
}
```

#### LoadingDialog

```
LoadingBar.dialog(context)
        //通过工厂设置样式
        //.setFactory(new CustomDialogFactory())
        //通过View设置样式
        //.setFactoryFromView(view)
        //通过布局ID设置样式
        .setFactoryFromResource(R.layout.layout_custom)
        //携带参数
        .extras(new Object[]{})
        .show();

//通过工厂设置样式
public class CustomDialogFactory implements LoadingFactory<Context, Dialog> {
    /**
     * 工厂类的标识符
     * 在cancel()之前多次调用show()时，当key相同时不会重新调用onCreate
     * @return
     */
    @Override
    public String getKey() {
        return getClass().getName();
    }

    @Override
    public Dialog onCreate(Context params) {
        //这里return的dialog就是Loading的样式
        return new AlertDialog.Builder(params)
                .setView(R.layout.layout_custom)
                .setCancelable(false)
                .create();
    }

    @Override
    public void updateStatus(Object[] extras) {
    	//每次调用show会回调这里  还有携带的参数
    }
}
```

如果是通过`匿名内部类`创建的`Factory`，`getKey()`需要自定义以保证可以区分样式，例如用`layoutId`区分

```
private LoadingFactory<ViewGroup, View> createViewFactoryFromResource(@LayoutRes int layoutId) {
        return new LoadingFactory<ViewGroup, View>() {

            @Override
            public String getKey() {
                return String.format(Locale.getDefault(), "ViewFactoryFromResource@%d", layoutId);
            }

            @Override
            public View onCreate(ViewGroup parent) {
                return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            }

            @Override
            public void updateStatus(Object[] extras) {

            }
        };
    }
```

## 截图
<img src="screenshots/loadingbar.gif" width="250"/>