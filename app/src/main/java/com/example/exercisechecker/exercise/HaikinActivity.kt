package com.example.exercisechecker.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.DateValueSanitizer
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import com.example.exercisechecker.R
import com.example.exercisechecker.TitleFragment
import com.example.exercisechecker.manager.DateManager
import com.example.exercisechecker.models.HaikinModel
import com.example.exercisechecker.storage.DBContract
import com.example.exercisechecker.storage.ExerciseHelper
import kotlinx.android.synthetic.main.activity_haikin.*

/*
背筋についてのアクティビティ
 */
class HaikinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haikin)

        val fragment = titleFragment as? TitleFragment
        fragment?.setTitle("背筋")

        val sqlHelper = ExerciseHelper(this)
        val database = sqlHelper.readableDatabase

        val c = database.query(
            DBContract.HaikinEntry.TABLE_NAME, arrayOf(
                "rowid as _id",
                DBContract.HaikinEntry.COUNT, DBContract.HaikinEntry.DATE
            ), null, null, null, null,
            null
        )

        c.moveToFirst()

        val adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            c,
            arrayOf(DBContract.HaikinEntry.COUNT, DBContract.HaikinEntry.DATE),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        val listView = findViewById<ListView>(R.id.haikinList)
        listView.adapter = adapter

        saveButton.setOnClickListener {
            val count = haikinEdit.text.toString().toInt()   // ここ怪しい 多分一回toStringしないと無理っぽい
            val date = DateManager().getCurrentDate()

            val helper = ExerciseHelper(this)
            val haikin = HaikinModel(count, date)
            helper.insertHaikin(haikin)
        }
    }

}
