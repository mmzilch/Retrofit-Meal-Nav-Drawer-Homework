package com.example.retrofitmealnavdrawerhomework.ui.Api

import com.example.retrofitmealnavdrawerhomework.ui.Model.Beef
import com.example.retrofitmealnavdrawerhomework.ui.Model.Chicken
import com.example.retrofitmealnavdrawerhomework.ui.Model.Seafood
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealApi {

    val mealApiInterface: MealApiInterface

    companion object{
        const val Base_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mealApiInterface = retrofit.create(MealApiInterface::class.java)
    }

        fun getChicken(c:String): Call<Chicken> {
            return mealApiInterface.getChicken(c)
        }

        fun getBeef(c:String) : Call<Beef>{
            return mealApiInterface.getBeef(c)
        }

        fun getSeafood():Call<Seafood>{
            return mealApiInterface.getSeafood()
        }
}