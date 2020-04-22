package com.example.exercisechecker.exercise

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import com.example.exercisechecker.R
import com.example.exercisechecker.TitleFragment
import com.example.exercisechecker.manager.DateManager
import com.example.exercisechecker.models.RunningModel
import com.example.exercisechecker.storage.DBContract
import com.example.exercisechecker.storage.ExerciseHelper
import kotlinx.android.synthetic.main.activity_running.saveButton
import kotlinx.android.synthetic.main.activity_running.titleFragment
import kotlinx.android.synthetic.main.activity_running.*

class RunningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_running)

        val fragment = titleFragment as? TitleFragment
        fragment?.setTitle("ランニング")

        val sqlHelper = ExerciseHelper(this)
        val database = sqlHelper.readableDatabase

        val c = database.query(DBContract.RunningEntry.TABLE_NAME, arrayOf("rowid as _id",
            DBContract.RunningEntry.TIME, DBContract.RunningEntry.DATE), null, null, null, null,
            null)
        c.moveToFirst()

        val adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            c,
            arrayOf(DBContract.RunningEntry.TIME, DBContract.RunningEntry.DATE),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        val listView = findViewById<ListView>(R.id.runningList)
        listView.adapter = adapter

        saveButton.setOnClickListener {
            val count = runningEdit.text.toString().toInt()   // ここ怪しい 多分一回toStringしないと無理っぽい
            val date = DateManager().getCurrentDate()

            val helper = ExerciseHelper(this)
            val running = RunningModel(count, date)
            helper.insertRunning(running)
        }
    }
}
