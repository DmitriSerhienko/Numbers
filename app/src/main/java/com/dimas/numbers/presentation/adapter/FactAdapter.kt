package com.dimas.numbers.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dimas.numbers.R
import com.dimas.numbers.data.db.FactsEntity
import com.dimas.numbers.presentation.StartFragmentDirections

class FactAdapter: RecyclerView.Adapter<FactViewHolder>() {

    private var numbersList = emptyList<FactsEntity>()

    fun setData(newData: List<FactsEntity>) {
        val numbersDiffUtil = FactsDiffCallback(numbersList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(numbersDiffUtil)
        numbersList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.number_item, parent, false)
        return FactViewHolder(view)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        val curFact = numbersList[position]
        holder.bind(curFact)

        holder.numberRowLayout.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToDetailsFragment(curFact.facts)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = numbersList.size

}