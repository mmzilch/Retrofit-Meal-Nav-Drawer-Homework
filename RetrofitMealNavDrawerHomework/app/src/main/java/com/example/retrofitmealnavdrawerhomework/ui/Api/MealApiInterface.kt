package com.example.retrofitmealnavdrawerhomework.ui.Api

import com.example.retrofitmealnavdrawerhomework.ui.Model.Beef
import com.example.retrofitmealnavdrawerhomework.ui.Model.Chicken
import com.example.retrofitmealnavdrawerhomework.ui.Model.Meal
import com.example.retrofitmealnavdrawerhomework.ui.Model.Seafood
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiInterface {
    @GET("filter.php")
    fun getChicken(@Query("c")c:String): Call<Chicken>
    
    @GET("filter.php")
    fun getBeef(@Query("c")c:String):Call<Beef>

    @GET("filter.php?c=seafood")
    fun getSeafood():Call<Seafood>
}