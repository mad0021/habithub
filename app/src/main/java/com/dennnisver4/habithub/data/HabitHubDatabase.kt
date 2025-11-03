package com.dennnisver4.habithub.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        DailyNote::class,
        MonthlyObjective::class,
        ObjectiveCompletion::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class HabitHubDatabase : RoomDatabase() {
    abstract fun habitHubDao(): HabitHubDao

    companion object {
        @Volatile
        private var INSTANCE: HabitHubDatabase? = null

        fun getDatabase(context: Context): HabitHubDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        HabitHubDatabase::class.java,
                        "habithub_database",
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
