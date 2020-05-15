package com.example.exercisechecker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercisechecker.exercise.*
import kotlinx.android.synthetic.main.activity_main.*

/*
メニュー画面
どのメニューを行うか選択する
1. 腹筋
2. 腕立て
3. 背筋
4. ランニング
5. スクワット
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // タイトルフラグメント
        val fragment = titleFragment as? TitleFragment
        fragment?.setTitle("運動管理アプリ")
        

        // 各運動名のボタンを押すとページ遷移
        hukkinButton.setOnClickListener {
            val intent = Intent(this, HukkinActivity::class.java)
            startActivity(intent)
        }

        udetateButton.setOnClickListener {
            val intent = Intent(this, UdetateActivity::class.java)
            startActivity(intent)
        }

        haikinButton.setOnClickListener {
            val intent = Intent(this, HaikinActivity::class.java)
            startActivity(intent)
        }

        runningButton.setOnClickListener {
            val intent = Intent(this, RunningActivity::class.java)
            startActivity(intent)
        }

        squatButton.setOnClickListener {
            val intent = Intent(this, SquatActivity::class.java)
            startActivity(intent)
        }

    }
}
