package com.ismoil.lugat.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ismoil.lugat.room.dao.ThemeDao
import com.ismoil.lugat.room.dao.WordDao
import com.ismoil.lugat.room.entity.Theme
import com.ismoil.lugat.room.entity.Word

@Database(entities = [Theme::class, Word::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun themeDao(): ThemeDao
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                instance = newInstance
                newInstance
            }
        }
    }
}
