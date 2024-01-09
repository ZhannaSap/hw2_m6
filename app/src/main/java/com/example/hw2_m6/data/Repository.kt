package com.example.hw2_m6.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hw2_m6.data.model.BaseResponse
import com.example.hw2_m6.data.model.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: AppApiService) {
    fun getCharacters(): MutableLiveData<Resource<List<Character>>> {
        val characters = MutableLiveData<Resource<List<Character>>>()
        characters.postValue(Resource.Loading())
        api.getCharacters().enqueue(object : Callback<BaseResponse<Character>> {
            override fun onResponse(
                call: Call<BaseResponse<Character>>,
                response: Response<BaseResponse<Character>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        characters.postValue(Resource.Success(it.results))
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<Character>>, t: Throwable) {
                characters.postValue(Resource.Error(t.localizedMessage?: "Unknowm error"))
                Log.e("ololo", t.message.toString())
            }

        })
        return characters
    }

    fun getCharacter(id: Int): LiveData<Resource<Character>> {
        val characterLv = MutableLiveData<Resource<Character>>()
        characterLv.postValue(Resource.Loading())
        api.getCharacter(id).enqueue(object : Callback<Character> {
            override fun onResponse(
                call: Call<Character>,
                response: Response<Character>
            ) {
                response?.body().let {
                    characterLv.postValue(Resource.Success(response.body()!!))
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                characterLv.postValue(Resource.Error(t.localizedMessage?: "Unknowm error"))
                Log.e("ololo", t.message.toString())
            }
        })
        return characterLv
    }
}
