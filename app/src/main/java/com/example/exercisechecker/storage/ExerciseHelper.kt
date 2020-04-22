package com.example.exercisechecker.storage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.exercisechecker.models.*
import java.sql.SQLException

class ExerciseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    @Throws(SQLiteException::class)
    fun insertHukkin(hukkin: HukkinModel): Boolean {
        val database = writableDatabase

        val values = ContentValues()
        values.put(DBContract.HukkinEntry.COUNT, hukkin.count)
        values.put(DBContract.HukkinEntry.DATE, hukkin.date)

        val rowId = database.insert(DBContract.HukkinEntry.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLException::class)
    fun insertUdetate(udetate: UdetateModel): Boolean {
        val database = writableDatabase

        val values = ContentValues()
        values.put(DBContract.UdetateEntry.COUNT, udetate.count)
        values.put(DBContract.UdetateEntry.DATE, udetate.date)

        val rowId = database.insert(DBContract.UdetateEntry.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLException::class)
    fun insertHaikin(haikin: HaikinModel): Boolean {
        val database = writableDatabase

        val values = ContentValues()
        values.put(DBContract.HaikinEntry.COUNT, haikin.count)
        values.put(DBContract.HaikinEntry.DATE, haikin.date)

        val rowId = database.insert(DBContract.HaikinEntry.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLiteException::class)
    fun insertRunning(running: RunningModel): Boolean {
        val database = writableDatabase

        val values = ContentValues()
        values.put(DBContract.RunningEntry.TIME, running.time)
        values.put(DBContract.RunningEntry.DATE, running.date)

        val rowId = database.insert(DBContract.RunningEntry.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLiteException::class)
    fun insertSquat(squat: SquatModel): Boolean {
        val database = writableDatabase

        val values = ContentValues()
        values.put(DBContract.SquatEntry.COUNT, squat.count)
        values.put(DBContract.SquatEntry.DATE, squat.date)

        val rowId = database.insert(DBContract.SquatEntry.TABLE_NAME, null, values)

        return true
    }

    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL(HUKKIN_CREATE_SENT)
        database?.execSQL(UDETATE_CREATE_SENT)
        database?.execSQL(HAIKIN_CREATE_SENT)
        database?.execSQL(RUNNING_CREATE_SENT)
        database?.execSQL(SQUAT_CREATE_SENT)
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        database?.execSQL(HUKKIN_DELETE_SENT)
        database?.execSQL(HUKKIN_CREATE_SENT)
        database?.execSQL(UDETATE_DELETE_SENT)
        database?.execSQL(UDETATE_CREATE_SENT)
        database?.execSQL(HAIKIN_DELETE_SENT)
        database?.execSQL(HAIKIN_CREATE_SENT)
        database?.execSQL(RUNNING_DELETE_SENT)
        database?.execSQL(RUNNING_CREATE_SENT)
        database?.execSQL(SQUAT_DELETE_SENT)
        database?.execSQL(SQUAT_CREATE_SENT)
    }

    override fun onDowngrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(database, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_NAME = "EXERCISE.db"
        const val DATABASE_VERSION = 1

        private val HUKKIN_CREATE_SENT =
            "CREATE TABLE " + DBContract.HukkinEntry.TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT " +
                    DBContract.HukkinEntry.COUNT + " INTEGER ," +
                    DBContract.HukkinEntry.DATE + " INTEGER)"

        private val UDETATE_CREATE_SENT =
            "CREATE TABLE " + DBContract.UdetateEntry.TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT " +
                    DBContract.UdetateEntry.COUNT + " INTEGER ," +
                    DBContract.UdetateEntry.DATE + " INTEGER)"

        private val HAIKIN_CREATE_SENT =
            "CREATE TABLE " + DBContract.HaikinEntry.TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT " +
                    DBContract.HaikinEntry.COUNT + " INTEGER ," +
                    DBContract.HaikinEntry.DATE + " INTEGER)"

        private val RUNNING_CREATE_SENT =
            "CREATE TABLE " + DBContract.RunningEntry.TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT " +
                    DBContract.RunningEntry.TIME + " INTEGER ," +
                    DBContract.RunningEntry.DATE + " INTEGER)"

        private val SQUAT_CREATE_SENT =
            "CREATE TABLE " + DBContract.SquatEntry.TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT " +
                    DBContract.SquatEntry.COUNT + " INTEGER ," +
                    DBContract.SquatEntry.DATE + " INTEGER)"

        private val HUKKIN_DELETE_SENT =
            "DROP TABLE IF EXISTS " + DBContract.HukkinEntry.TABLE_NAME

        private val UDETATE_DELETE_SENT =
            "DROP TABLE IF EXISTS " + DBContract.UdetateEntry.TABLE_NAME

        private val HAIKIN_DELETE_SENT =
            "DROP TABLE IF EXISTS " + DBContract.HaikinEntry.TABLE_NAME

        private val RUNNING_DELETE_SENT =
            "DROP TABLE IF EXISTS " + DBContract.RunningEntry.TABLE_NAME

        private val SQUAT_DELETE_SENT =
            "DROP TABLE IF EXISTS " + DBContract.SquatEntry.TABLE_NAME
    }

}