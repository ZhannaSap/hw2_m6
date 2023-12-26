package com.example.hw2_m6.ui.main_activity.adpter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.hw2_m6.R
import com.example.hw2_m6.databinding.ItemCaharacterBinding
import com.example.hw2_m6.data.model.Character
import com.example.hw2_m6.ui.utils.Status

class CharacterAdapter(
    private val onClick: (character: Character) -> Unit,
    private var list: List<Character>
) : Adapter<CharacterAdapter.CharacterViewHolder>() {

//    private var list = listOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCaharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submitList(list: List<Character>) {
      this.list =list
    }

    inner class CharacterViewHolder(private val binding: ItemCaharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun bind(character: Character) {
            binding.run {
                Glide.with(imageCharacter).load(character.image)
                    .into(imageCharacter)
                tvCharacterName.text = character.name
                tvStatus.text = character.status
                tvSpecies.text = character.species
                tvLocationInfo.text = character.location.name
                itemView.setOnClickListener {
                    onClick(character)
                }

                when (character.status){
                    Status.ALIVE.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle_green)
                    Status.DEAD.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle_red)
                    Status.UNKNOWN.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle)
                }
            }
        }
    }
}