package com.example.fronteravictor_examenfinal.connection

import com.example.fronteravictor_examenfinal.model.Player
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("players")
    fun getPlayers(): Call<ArrayList<Player>>

    @DELETE("players/{id}")
    fun deletePlayer(@Path("id") id: Int): Call<Player>
}