package com.twitter.challenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.twitter.challenge.databinding.ItemLayoutBinding
import com.twitter.challenge.model.WeatherListUI
import com.twitter.challenge.model.WeatherUI

class MainAdapter(
    private val users: ArrayList<WeatherUI>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemBinding: ItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(user: WeatherUI) {
            itemBinding.textViewUserName.text = user.name
            itemBinding.textViewUserEmail.text = user.toString()
            Glide.with(itemBinding.imageViewAvatar.context)
                .load(android.R.drawable.ic_media_play)
                .into(itemBinding.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding: ItemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addData(list: WeatherListUI) {
        list.weatherList?.let { users.addAll(it) }
    }
}