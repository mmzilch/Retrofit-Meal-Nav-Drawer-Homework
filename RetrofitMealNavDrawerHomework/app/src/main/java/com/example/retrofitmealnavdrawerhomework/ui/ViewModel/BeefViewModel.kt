package com.example.retrofitmealnavdrawerhomework.ui.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitmealnavdrawerhomework.ui.Api.MealApi
import com.example.retrofitmealnavdrawerhomework.ui.Model.Beef
import com.example.retrofitmealnavdrawerhomework.ui.Model.Meal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeefViewModel : ViewModel() {

    val mealResult : MutableLiveData<List<Meal>> = MutableLiveData()
    val loading : MutableLiveData<Boolean> = MutableLiveData()
    val resultLoaderError : MutableLiveData<Boolean> = MutableLiveData()

    fun getMealResult() : LiveData<List<Meal>> = mealResult
    fun getError() : LiveData<Boolean> = resultLoaderError
    fun getLoading () : LiveData<Boolean> = loading

    private val mealApi : MealApi = MealApi()

    fun loadBeef(c: String)
    {
        loading.value = true
        val apiCall = mealApi.getBeef(c)

        apiCall.enqueue(object : Callback<Beef> {
            override fun onFailure(call: Call<Beef>, t: Throwable) {
                resultLoaderError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<Beef>, response: Response<Beef>) {
                response.isSuccessful.let {
                    loading.value = false
                    val beefList : List<Meal> = response.body()?.meals?: emptyList()
                    Log.d("meallist>>>",beefList.toString())
                    Log.d("responseBody>>>",response.body().toString())
                    mealResult.value = beefList
                }
            }

        })
    }
}