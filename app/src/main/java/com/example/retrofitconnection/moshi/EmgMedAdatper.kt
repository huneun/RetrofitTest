package com.example.retrofitconnection.moshi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitconnection.databinding.ItemLayoutBinding

class EmgMedAdatper : ListAdapter<Row, EmgMedAdatper.EmgMedViewHolder>(EmgmedCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmgMedViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false )
        return EmgMedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmgMedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EmgMedViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Row) {
            with(binding) {
                tvName.text = item.cENTERNM
                tvType.text = item.cENTERTYPE
                tvPhone.text = item.oPRINSTNM
                tvAddr.text = item.aDDR
                tvWgs84lat.text = "위도: ${item.rEFINEWGS84LAT}"
                tvWgs84lon.text = "경도: ${item.rEFINEWGS84LOGT}"
            }
        }
    }

    companion object {
        private val EmgmedCallback = object : DiffUtil.ItemCallback<Row>(){
            override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
                return oldItem == newItem
            }
        }
    }



}