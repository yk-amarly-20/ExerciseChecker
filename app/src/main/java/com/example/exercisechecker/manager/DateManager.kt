package com.example.exercisechecker.manager

import java.text.SimpleDateFormat
import java.util.*

/*
日付型の管理
 */

class DateManager {

    /*
    現在時刻をLong型で取得
     */
    fun getCurrentDate() :Long {
        return System.currentTimeMillis()
    }

    /*
    Long型をString型に変形
     */
    fun convertLongToString(dateLong: Long) :String {
        val date = Date(dateLong)
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
        return simpleDateFormat.format(date)
    }
}