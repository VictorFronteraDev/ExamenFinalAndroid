package com.example.fronteravictor_examenfinal.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoFilms {
    @Query("SELECT * FROM film_table ORDER BY  id ASC")
    fun getFimls() : LiveData<List<Film>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(film: Film)

//    @Query("SELECT * FROM film_table WHERE  duration =:duration > duration ")
//    fun get(duration: Int)
}