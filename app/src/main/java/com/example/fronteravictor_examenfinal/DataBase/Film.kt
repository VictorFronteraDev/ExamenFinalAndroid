package com.example.fronteravictor_examenfinal.DataBase

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_table")
data class Film(@ColumnInfo(name = "name") var name: String, @ColumnInfo(name = "duration") var duration: Int) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = 0
}