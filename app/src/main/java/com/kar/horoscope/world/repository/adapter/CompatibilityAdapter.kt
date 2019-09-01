package com.kar.horoscope.world.repository.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kar.horoscope.world.R
import com.kar.horoscope.world.service.ItemClickedCallback
import kotlinx.android.synthetic.main.name.view.*

class CompatibilityAdapter
    ( private val images: Array<Int>,
      private val names: Array<String>,
      private val itemClickedCallback: ItemClickedCallback): RecyclerView.Adapter<CompatibilityAdapter.CompatibilityViewHolder>() {

    class CompatibilityViewHolder( val card: View) : RecyclerView.ViewHolder ( card )


    override fun onCreateViewHolder(parent: ViewGroup, i: Int): CompatibilityViewHolder {
        return CompatibilityViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.name, parent, false ) )
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: CompatibilityViewHolder, i: Int) {
        with(holder) {
            with ( card ) {
                zodiacName.text = names[i]
            }
        }

        holder.card.setOnClickListener {
            itemClickedCallback.itemClicked ( names[i], images[i] )
        }
    }
}