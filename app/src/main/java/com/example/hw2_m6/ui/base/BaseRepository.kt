package com.example.hw2_m6.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.hw2_m6.data.AppApiService
import com.example.hw2_m6.data.Resource
import com.example.hw2_m6.data.model.BaseResponse
import com.example.hw2_m6.data.model.Character
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

abstract class BaseRepository(private val api: AppApiService) {

    fun <T> apiRequest(apiCall: suspend () -> T): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = apiCall.invoke()
                if (response != null) {
                    emit(Resource.Success(response!!))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            }

        }
}
/*fun getCharacter(id: Int): LiveData<Resource<Character>> = liveData(Dispatchers.IO){
       emit(Resource.Loading())
        try{
            val response = api.getCharacter(id)
            if (response.isSuccessful && response.body() != null){
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }
        }catch (ex:Exception){
            emit(Resource.Error(ex.localizedMessage?:"Unknown error"))
        }
    }*/