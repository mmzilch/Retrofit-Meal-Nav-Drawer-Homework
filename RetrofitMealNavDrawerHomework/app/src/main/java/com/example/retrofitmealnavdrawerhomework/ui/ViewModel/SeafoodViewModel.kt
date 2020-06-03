package com.example.retrofitmealnavdrawerhomework.ui.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitmealnavdrawerhomework.ui.Api.MealApi
import com.example.retrofitmealnavdrawerhomework.ui.Model.Meal
import com.example.retrofitmealnavdrawerhomework.ui.Model.Seafood
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeafoodViewModel : ViewModel() {

    val mealResult : MutableLiveData<List<Meal>> = MutableLiveData()
    val loading : MutableLiveData<Boolean> = MutableLiveData()
    val resultLoaderError : MutableLiveData<Boolean> = MutableLiveData()

    fun getMealResult() : LiveData<List<Meal>> = mealResult
    fun getError() : LiveData<Boolean> = resultLoaderError
    fun getLoading () : LiveData<Boolean> = loading

    private val mealApi : MealApi = MealApi()

    fun loadSeafood(c:String)
    {
        loading.value = true
        val apiCall = mealApi.getSeafood()
        apiCall.enqueue(object : Callback<Seafood> {
            override fun onFailure(call: Call<Seafood>, t: Throwable) {
                resultLoaderError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<Seafood>, response: Response<Seafood>) {
                response.isSuccessful.let {
                    loading.value = false
                    val seafoodList : List<Meal> = response.body()?.meals?: emptyList()
                    Log.d("meallist>>>",seafoodList.toString())
                    Log.d("responseBody>>>",response.body().toString())
                    mealResult.value = seafoodList
                }
            }

        })
    }
}