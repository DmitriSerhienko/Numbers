package com.dimas.numbers.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dimas.numbers.data.db.FactsEntity
import com.dimas.numbers.databinding.NumberItemBinding

class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = NumberItemBinding.bind(itemView)
    val numberRowLayout = binding.numberLayout

    fun bind(result: FactsEntity) = with(binding) {
        titleNumber.text = result.facts.number.toString()
        textAboutNumber.text = result.facts.aboutNumber
    }
}