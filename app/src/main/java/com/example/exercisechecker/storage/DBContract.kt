package com.example.exercisechecker.storage

import android.provider.BaseColumns

/*
データベース全体の情報を、インナークラスとして保持
 */
object DBContract {

    class HukkinEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "hukkin"
            const val COUNT = "count"
            const val DATE = "date"
        }
    }

    class UdetateEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "udetate"
            const val COUNT = "count"
            const val DATE = "date"
        }
    }

    class HaikinEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "haikin"
            const val COUNT = "count"
            const val DATE = "date"
        }
    }

    class RunningEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "running"
            const val TIME = "time"
            const val DATE = "date"
        }
    }

    class SquatEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "squat"
            const val COUNT = "count"
            const val DATE = "date"
        }
    }

}