package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache

import io.flutter.embedding.engine.dart.DartExecutor





class MyFlutterActivity : AppCompatActivity() {
    var flutterEngine: FlutterEngine? =null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter)
//        //创建一个FlutterView
//        val flutterView: FlutterView = Flutter.createView(this, lifecycle, "route1")
//        //实例化容器
//        val layout = findViewById<FrameLayout>(R.id.flutter_container)
//        //将FlutterView添加到容器中去
//        layout.addView(flutterView)
//        //解决原生页面跳转Flutter页面黑屏的问题（原理就是先让界面隐藏，等第一帧绘制完成后，再让他显示出来）
//        val listeners = arrayOfNulls<FirstFrameListener>(1)
//        listeners[0] = FirstFrameListener { layout.visibility = View.VISIBLE }
//        flutterView.addFirstFrameListener(listeners[0])

//        flutterEngine = FlutterEngine(this)
//        flutterEngine?.dartExecutor.executeDartEntrypoint(
//            DartExecutor.DartEntrypoint.createDefault()
//        )
//        flutterEngine?.navigationChannel.setInitialRoute("route1");
//// 通过FlutterView引入Flutter编写的页面
//        // 通过FlutterView引入Flutter编写的页面
//        val flutterView = FlutterView(this)
//        val lp = FrameLayout.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT
//        )
//        val flContainer = findViewById<FrameLayout>(R.id.flutter_container)
//        flContainer.addView(flutterView, lp)
//// 关键代码，将Flutter页面显示到FlutterView中
//// 关键代码，将Flutter页面显示到FlutterView中
//        flutterView.attachToFlutterEngine(flutterEngine!!)







        //方式一
//        val flutterFragment = FlutterFragment.createDefault()
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.flutter_container, flutterFragment)
//            .commit()
        //方式二
//        // 通过FlutterFragment引入Flutter编写的页面
//        val flutterFragment = FlutterFragment.withNewEngine()
//            .initialRoute("route1")
//            .build<FlutterFragment>()
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.flutter_container, flutterFragment)
//            .commit()
        //方式三
        // 创建可缓存的FlutterEngine对象
        val flutterEngine = FlutterEngine(this)
        flutterEngine.navigationChannel.setInitialRoute("route1")
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        FlutterEngineCache.getInstance().put("my_engine_id", flutterEngine)
        // 通过FlutterFragment引入Flutter编写的页面
        val flutterFragment = FlutterFragment.withCachedEngine("my_engine_id")
            .build<FlutterFragment>()
    }

    override fun onResume() {
        super.onResume()
        flutterEngine?.lifecycleChannel?.appIsResumed()
    }

    override fun onPause() {
        super.onPause()
        flutterEngine?.lifecycleChannel?.appIsInactive()
    }

    override fun onStop() {
        super.onStop()
        flutterEngine?.lifecycleChannel?.appIsPaused()
    }
}