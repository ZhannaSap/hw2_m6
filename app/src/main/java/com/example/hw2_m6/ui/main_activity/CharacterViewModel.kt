package com.example.hw2_m6.ui.main_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw2_m6.data.Repository
import com.example.hw2_m6.data.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    fun getAllData(): LiveData<List<Character>> {
        return repository.getCharacters()
    }
}