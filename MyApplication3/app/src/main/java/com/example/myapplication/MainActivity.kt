package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnJumpToFlutter: Button = findViewById(R.id.button)
        btnJumpToFlutter.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this@MainActivity, MyFlutterActivity::class.java)
//            startActivity(intent)
            startActivity(
                FlutterActivity
                    .withNewEngine()
                    .initialRoute("route1")
                    .build(this)
            );
        })
    }
}