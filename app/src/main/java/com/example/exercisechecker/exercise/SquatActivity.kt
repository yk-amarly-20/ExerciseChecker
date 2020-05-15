package com.example.exercisechecker.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import com.example.exercisechecker.R
import com.example.exercisechecker.TitleFragment
import com.example.exercisechecker.manager.DateManager
import com.example.exercisechecker.models.SquatModel
import com.example.exercisechecker.storage.DBContract
import com.example.exercisechecker.storage.ExerciseHelper
import kotlinx.android.synthetic.main.activity_squat.*

class SquatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_squat)
        
        // タイトルフラグメント
        val fragment = titleFragment as? TitleFragment
        fragment?.setTitle("スクワット")

        // sql関係の諸々
        val sqlHelper = ExerciseHelper(this)
        val database = sqlHelper.readableDatabase

        val c = database.query(DBContract.SquatEntry.TABLE_NAME, arrayOf("rowid as _id",
            DBContract.SquatEntry.COUNT, DBContract.SquatEntry.DATE), null, null, null, null,
            null)

        // アダプター作成
        val adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            c,
            arrayOf(DBContract.SquatEntry.COUNT, DBContract.SquatEntry.DATE),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        // リストビューに表示
        val listView = findViewById<ListView>(R.id.squatList)
        listView.adapter = adapter

        // 保存ボタン
        saveButton.setOnClickListener {
            val count = squatEdit.text.toString().toInt()   // ここ怪しい 多分一回toStringしないと無理っぽい
            val date = DateManager().getCurrentDate()

            val helper = ExerciseHelper(this)
            val squat = SquatModel(count, date)
            helper.insertSquat(squat)
        }
    }
}
