package com.example.hw2_m6.ui.main_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw2_m6.data.Resource
import com.example.hw2_m6.data.model.Character
import com.example.hw2_m6.databinding.ActivityCharacterBinding
import com.example.hw2_m6.ui.base.BaseActivity
import com.example.hw2_m6.ui.main_activity.adpter.CharacterAdapter
import com.example.hw2_m6.ui.second_activity.DetailsActivity
import com.example.hw2_m6.ui.utils.CharacterKeys
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterActivity : BaseActivity() {

    private lateinit var binding: ActivityCharacterBinding
    private  val viewModel by viewModel<CharacterViewModel>()
    private val characterAdapter by lazy { CharacterAdapter(this::onClickItem) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCharactersRecycler()
        //viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

        viewModel.getCharacters().stateHandler(
            success = {
                characterAdapter.submitList(it)
            },
            state = { state ->
                binding.progressBar.isVisible = state is Resource.Loading
            }
        )


    }

    private fun setupCharactersRecycler() /*= with(binding.rv)*/ {
        binding.rv.layoutManager = LinearLayoutManager(
            this@CharacterActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rv.adapter = characterAdapter
    }

    private fun onClickItem(character: Character) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(CharacterKeys.CHARACTER_ID_ARG, character.id)
        startActivity(intent)
    }

}

/*
        viewModel.getCharacters().observe(this) {
            when (it) {
                is Resource.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    characterAdapter.submitList(it.data)
                }
            }
        }*/