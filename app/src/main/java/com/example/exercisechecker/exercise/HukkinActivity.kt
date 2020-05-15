package com.example.exercisechecker.exercise

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import com.example.exercisechecker.R
import com.example.exercisechecker.TitleFragment
import com.example.exercisechecker.manager.DateManager
import com.example.exercisechecker.models.HukkinModel
import com.example.exercisechecker.storage.DBContract
import com.example.exercisechecker.storage.ExerciseHelper
import kotlinx.android.synthetic.main.activity_hukkin.*
import kotlinx.android.synthetic.main.activity_hukkin.titleFragment


/*
腹筋についてのアクティビティ
 */
class HukkinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hukkin)

        /*
        フラグメントの表示
         */
        val fragment = titleFragment as? TitleFragment
        fragment?.setTitle("腹筋")

        /*
        データベースからデータを取得して、リストビューとして表示
         */
        val sqlHelper = ExerciseHelper(this)
        val database = sqlHelper.readableDatabase

        /*
        ここ今回のアプリで一番ハマった
        rowid as _idとしなければいけない（なんでか知らんけど）
         */
        val c = database.query(DBContract.HukkinEntry.TABLE_NAME, arrayOf("rowid as _id", DBContract.HukkinEntry.COUNT, DBContract.HukkinEntry.DATE), null, null, null, null, null)
        c.moveToFirst()

        /*
        アダプターの作成
        */
        val adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            c,
            arrayOf(DBContract.HukkinEntry.COUNT, DBContract.HukkinEntry.DATE),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        /*
        リストビューに表示
        /
        val listView = findViewById<ListView>(R.id.hukkinList)
        listView.adapter = adapter

        /*
        todo: 新規入力、saveボタン実装(済)
              日付表示おかしいから修正
         */
        saveButton.setOnClickListener {
            val count = hukkinEdit.text.toString().toInt()   // ここ怪しい 多分一回toStringしないと無理っぽい
            val date = DateManager().getCurrentDate()

            val helper = ExerciseHelper(this)
            val hukkin = HukkinModel(count, date)
            helper.insertHukkin(hukkin)
        }

    }
}
