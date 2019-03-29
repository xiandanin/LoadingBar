# LoadingBar

### Example APK
![](loadingbar-example.png)

## Quick Start
### Android Studio - Introduced in build.gradle
```java
implementation 'com.dyhdyh.loadingbar:loadingbar:1.4.9'

//appcompat
implementation "com.android.support:appcompat-v7:xxx"
```
#### LoadingBar
```java
//default style, loading will cover the contents of the parent
LoadingBar.make(parent).show();

//custom style
//two forms, LoadingView is easier, LoadingFactory higher degree of freedom
LoadingBar.make(parent,loadingView).show();
LoadingBar.make(parent,loadingFactory).show();

//fully customizable
LoadingBar.make(parent,loadingFactory)
        .setOnClickListener(clickListener)
        .setOnLoadingBarListener(loadingBarListener)
        .show();
        
//cancel loading
LoadingBar.cancel(parent);
```
![](Screenshot/loadingbar.gif)
#### LoadingDialog
```java
//default style
LoadingDialog.make(context).show();

//custom style
LoadingDialog.make(context, dialogFactory).show();

//fully customizable
LoadingDialog.make(context, dialogFactory)
           .setMessage(message)
           .setCancelable(cancelable)
           .show();

//set more properties
Dialog dialog = LoadingDialog.make(context, dialogFactory)
           .setMessage(message)
           .setCancelable(cancelable)
           .create();
dialog.setOnCancelListener(cancelListener);
dialog.set...
dialog.show();
           
//cancel loading
LoadingDialog.cancel();
```
![](Screenshot/loadingdialog.gif)

#### Customize Factory
```java
public class CustomLoadingFactory implements LoadingFactory {

    @Override
    public View onCreateView(ViewGroup parent) {
        View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_custom, parent,false);
        return loadingView;
    }
}
```


#### Global Configuration
```java
//customize styles and apply them to global
LoadingConfig.setFactory(loadingFactory,dialogFactory);

```
![](Screenshot/loading_config.gif)

#### Resource Release
In fact, LoadingBar in the cancel time, has been released,do not need to manually release,but here also provides a release method,according to their own needs.

If you want to manually call, I recommend at `BaseActivity` `onDestroy`,resource release only releases invalid resources.
```java
LoadingBar.release();
```
