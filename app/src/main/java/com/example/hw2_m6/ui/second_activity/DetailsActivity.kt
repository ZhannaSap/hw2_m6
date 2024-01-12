package com.example.hw2_m6.ui.second_activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.hw2_m6.R
import com.example.hw2_m6.data.Resource
import com.example.hw2_m6.data.model.Character
import com.example.hw2_m6.databinding.ActivityDetailsBinding
import com.example.hw2_m6.ui.base.BaseActivity
import com.example.hw2_m6.ui.utils.CharacterKeys
import com.example.hw2_m6.ui.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel by viewModel<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]

        val id = intent.getIntExtra(CharacterKeys.CHARACTER_ID_ARG, 0)

        viewModel.getData(id).stateHandler(
            success = {character->
                setCharacterData(character)
            },
            state = { state ->
                binding.progressBar.isVisible = state is Resource.Loading
            }
        )
    }

    private fun setCharacterData(it: Character) = with(binding) {
        Log.e("ololo", "Data is not null")
        tvCharacterName.text = it.name
        tvLocationInfo.text = it.location.toString()
        tvOriginInfo.text = it.origin.toString()
        tvSpecies.text = it.species
        tvStatus.text = it.status
        Glide.with(imageCharacter).load(it.image).into(imageCharacter)

        when (it.status) {
            Status.ALIVE.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle_green)
            Status.DEAD.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle_red)
            Status.UNKNOWN.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle)
        }
    }

}

/*        viewModel.getData(id).observe(this) {
            it?.let {
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
                        val character = it.data as Character
                        setCharacterData(character)
                    }
                }
            }
        }*/