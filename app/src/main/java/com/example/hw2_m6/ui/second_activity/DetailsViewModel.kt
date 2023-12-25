package com.example.hw2_m6.ui.second_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw2_m6.data.Repository
import com.example.hw2_m6.data.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getData(id: Int): LiveData<Character> {
        return repository.getCharacter(id)
    }
}