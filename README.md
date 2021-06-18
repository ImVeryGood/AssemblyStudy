#组件化

 - 创建一个app 作位壳工程。
 - 一个common library来写基础组件库，提供给module。
 - 创建module 模块。

1.版本管理在根目录创建`app_config.gradle`

    ext{
    //是否发布
    isRease=true
    app_android=[
            compileSdkVersion:30,
            buildToolsVersion :"30.0.2",
            minSdkVersion :26,
            targetSdkVersion: 30,
            versionCode :1,
            versionName :"1.0",
    ]

}
2.在module的gradle中引用

    android {
        compileSdkVersion app_android.compileSdkVersion
        buildToolsVersion app_android.buildToolsVersion
    }
3.判断是作位依赖打包还是独立运行

    isRease=true

4.在module中如果`isRease=true`则说明是作位依赖库
在module的gradle配置如下：

    if (isRease){
    apply plugin:'com.android.library'
    }else {
        apply plugin: 'com.android.application'
    }

    defaultConfig {
        if (!isRease){
            applicationId "com.m.order"
        }
    }
 在app添加module依赖时，如果`isRease` 作位library库引入

    if (isRease){
        implementation project(':order')
        implementation project(':personal')
    }
5.最后就是对`AndroidManifest`配置

在main下创建一个debug文件夹，拷贝AndroidManifest
然后项目目录下的AndroidManifest文件修改为：

    <?xml version="1.0" encoding="utf-8"?>
     <manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.m.personal">

    <application>
        <activity android:name=".PersonalMainActivity">
        </activity>
    </application>

</manifest>

在build文件中 `android{}`添加

    sourceSets{
        main{
            if (isRease){
                //集成化模式，整个项目打包
                manifest.srcFile 'src/main/AndroidManifest.xml'
                java {
                    exclude 'debug/**'
                }
            }else {
                //单独运行
                manifest.srcFile 'src/main/debug/AndroidManifest.xml'
            }
        }
    }

#ARouter使用

 首先添加依赖：

     api 'com.alibaba:arouter-api:1.4.0'
     annotationProcessor'com.alibaba:arouter-compiler:1.2.1'

在全部module包括module依赖的module（没有页面的module也算），在每个module的 build.gradle中加上下面的代码。

    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [AROUTER_MODULE_NAME: project.getName()]
            }
        }
    }
**特别注意：**module 的build 这个每个都要加    

    annotationProcessor'com.alibaba:arouter-compiler:1.2.1'

初始化：

    public class App extends Application {
    private boolean isDebug=true;
    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
           ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(App.this);
    }
}
使用：

    @Route(path = "/order/OrderMainActivity")
    public class OrderMainActivity extends AppCompatActivity {
    }
跳转：

     ARouter.getInstance().build("/order/OrderMainActivity").navigation();
    
