package com.example.hw2_m6.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.hw2_m6.data.model.Character
import com.example.hw2_m6.ui.base.BaseRepository
import kotlinx.coroutines.Dispatchers


class Repository(private val api: AppApiService) : BaseRepository(api) {
    fun getCharacters(): LiveData<Resource<List<Character>>> = apiRequest {
        api.getCharacters().body()?.results ?: emptyList()
    }


    fun getCharacter(id: Int): LiveData<Resource<Character>> = apiRequest {
        api.getCharacter(id).body()!!
    }
}
/* val characters = MutableLiveData<Resource<List<Character>>>()
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
        return characters*/

/* val characterLv = MutableLiveData<Resource<Character>>()
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
                characterLv.postValue(Resource.Error(t.localizedMessage ?: "Unknowm error"))
                Log.e("ololo", t.message.toString())
            }
        })
        return characterLv*/