package com.example.fronteravictor_examenfinal.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.Executors

@Database(
    entities = [Film::class],
    version = 1,
    exportSchema = false)

abstract class DataBaseRoom : RoomDatabase(){

    abstract fun fimlDao(): DaoFilms

    companion object {
        @Volatile
        private var INSTANCE : DataBaseRoom? = null

        private const val NUM_THREADS = 2

        val databaseWriterExecutor = Executors.newFixedThreadPool(NUM_THREADS)

        fun getData(context: Context) : DataBaseRoom {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        DataBaseRoom::class.java, "favourite_database")
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}