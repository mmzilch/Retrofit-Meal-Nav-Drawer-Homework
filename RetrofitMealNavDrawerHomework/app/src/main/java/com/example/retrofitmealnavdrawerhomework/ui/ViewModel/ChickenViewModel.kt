package com.example.retrofitmealnavdrawerhomework.ui.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitmealnavdrawerhomework.ui.Api.MealApi
import com.example.retrofitmealnavdrawerhomework.ui.Model.Beef
import com.example.retrofitmealnavdrawerhomework.ui.Model.Chicken
import com.example.retrofitmealnavdrawerhomework.ui.Model.Meal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChickenViewModel : ViewModel() {

    val mealResult : MutableLiveData<List<Meal>> = MutableLiveData()
    val loading : MutableLiveData<Boolean> = MutableLiveData()
    val resultLoaderError : MutableLiveData<Boolean> = MutableLiveData()

    fun getMealResult() : LiveData<List<Meal>> = mealResult
    fun getError() : LiveData<Boolean> = resultLoaderError
    fun getLoading () : LiveData<Boolean> = loading

    private val mealApi : MealApi = MealApi()

    fun loadChicken(c: String)
    {
        loading.value = true
        val apiCall = mealApi.getChicken(c)

        apiCall.enqueue(object : Callback<Chicken> {
            override fun onFailure(call: Call<Chicken>, t: Throwable) {
                resultLoaderError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<Chicken>, response: Response<Chicken>) {
                response.isSuccessful.let {
                    loading.value = false
                    val chickenList : List<Meal> = response.body()?.meals?: emptyList()
                    Log.d("meallist>>>",chickenList.toString())
                    Log.d("responseBody>>>",response.body().toString())
                    mealResult.value = chickenList
                }
            }

        })
    }
}