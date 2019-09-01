package com.kar.horoscope.world.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.kar.horoscope.world.R
import com.kar.horoscope.world.repository.fragmentadapter.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_forecast.*

class Forecast : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        val intent = intent
        val id = intent.getIntExtra( "Title", 0 )
        val list = resources.getStringArray(R.array.Zodiacs).toList()
        title = list[id]
        setImage( title.toString() )

        val myPagerAdapter = MainPagerAdapter ( supportFragmentManager, applicationContext )
        viewPager.adapter = myPagerAdapter
        viewPager.setCurrentItem(1, true )

        tabLayout.setupWithViewPager( viewPager )
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

    }

    private fun setImage(title: String?) {
        if (title.equals("Aries")) Glide.with(applicationContext).load(R.drawable.aries).into(forecastImage)
        if (title.equals("Taurus")) Glide.with(applicationContext).load(R.drawable.taurus).into(forecastImage)
        if (title.equals("Gemini")) Glide.with(applicationContext).load(R.drawable.gemini).into(forecastImage)
        if (title.equals("Cancer")) Glide.with(applicationContext).load(R.drawable.cancer).into(forecastImage)
        if (title.equals("Leo")) Glide.with(applicationContext).load(R.drawable.leo).into(forecastImage)
        if (title.equals("Virgo")) Glide.with(applicationContext).load(R.drawable.virgo).into(forecastImage)
        if (title.equals("Libra")) Glide.with(applicationContext).load(R.drawable.libra).into(forecastImage)
        if (title.equals("Scorpio")) Glide.with(applicationContext).load(R.drawable.scorpio).into(forecastImage)
        if (title.equals("Sagittarius")) Glide.with(applicationContext).load(R.drawable.sagittarius).into(forecastImage)
        if (title.equals("Capricorn")) Glide.with(applicationContext).load(R.drawable.capricorn).into(forecastImage)
        if (title.equals("Aquarius")) Glide.with(applicationContext).load(R.drawable.aquarius).into(forecastImage)
        if (title.equals("Pisces")) Glide.with(applicationContext).load(R.drawable.pisces).into(forecastImage)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        super.onBackPressed()
    }
}
