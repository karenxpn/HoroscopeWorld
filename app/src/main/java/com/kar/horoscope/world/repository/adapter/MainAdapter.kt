package com.kar.horoscope.world.repository.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kar.horoscope.world.R
import com.kar.horoscope.world.models.MainViewZodiacModel
import com.kar.horoscope.world.view.activities.Forecast
import kotlinx.android.synthetic.main.single_zodiac.view.*



class MainAdapter(
    private val list: List<MainViewZodiacModel>,
    private val context: Context
) : RecyclerView.Adapter<MainAdapter.ZodiacViewHolder>() {

    class ZodiacViewHolder ( val card: View) : RecyclerView.ViewHolder ( card )


    override fun onBindViewHolder(holder: ZodiacViewHolder, i: Int) {
        with ( holder ) {
            with ( card ) {
                zodiacName.text = list[i].name
                Glide.with( context ).load(list[i].image).into( zodiacImage )
            }
        }

        holder.card.setOnClickListener {
            val intent = Intent ( context, Forecast::class.java)
            intent.putExtra ( "Title", i )
            context.startActivity( intent )
            (context as Activity).overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            context.finish()

        }
    }


    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ZodiacViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_zodiac, parent, false )
        return ZodiacViewHolder( view )
    }
}