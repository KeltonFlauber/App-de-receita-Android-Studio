package com.example.receitasrapidas.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.receitasrapidas.DataBase.Dao.RecipesDao
import com.example.receitasrapidas.DataBase.ModelDB.FavRecipe

@Database(entities = [FavRecipe::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun favDao(): RecipesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}