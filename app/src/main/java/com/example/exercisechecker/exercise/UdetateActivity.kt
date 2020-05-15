package com.example.exercisechecker.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import com.example.exercisechecker.R
import com.example.exercisechecker.TitleFragment
import com.example.exercisechecker.manager.DateManager
import com.example.exercisechecker.models.UdetateModel
import com.example.exercisechecker.storage.DBContract
import com.example.exercisechecker.storage.ExerciseHelper
import kotlinx.android.synthetic.main.activity_udetate.saveButton
import kotlinx.android.synthetic.main.activity_udetate.titleFragment
import kotlinx.android.synthetic.main.activity_udetate.*

/*
腕立てについてのアクティビティ
 */
class UdetateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_udetate)

        // タイトルフラグメント
        val fragment = titleFragment as? TitleFragment
        fragment?.setTitle("腕立て")
        
        // sql関係の諸々
        val sqlHelper = ExerciseHelper(this)
        val database = sqlHelper.readableDatabase

        val c = database.query(DBContract.UdetateEntry.TABLE_NAME, arrayOf("rowid as _id",
            DBContract.UdetateEntry.COUNT, DBContract.UdetateEntry.DATE),null, null, null, null,
            null)

        c.moveToFirst()

        // アダプター作成
        val adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            c,
            arrayOf(DBContract.UdetateEntry.COUNT, DBContract.UdetateEntry.DATE),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        // リストビューに表示
        val listview = findViewById<ListView>(R.id.udetateList)
        listview.adapter = adapter

        // 保存ボタン
        saveButton.setOnClickListener {
            val count = udetateEdit.text.toString().toInt()   // ここ怪しい 多分一回toStringしないと無理っぽい
            val date = DateManager().getCurrentDate()

            val helper = ExerciseHelper(this)
            val udetate = UdetateModel(count, date)
            helper.insertUdetate(udetate)
        }

    }
}
