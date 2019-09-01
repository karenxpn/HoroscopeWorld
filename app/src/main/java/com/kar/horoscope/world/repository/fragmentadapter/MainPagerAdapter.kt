package com.kar.horoscope.world.repository.fragmentadapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kar.horoscope.world.R
import com.kar.horoscope.world.view.PostFragment


internal class MainPagerAdapter(fm: FragmentManager, private val context: Context) :
    FragmentStatePagerAdapter(fm) {

    private val dates: Array<String> = context.resources.getStringArray(R.array.date)


    override fun getItem(position: Int): Fragment {
        return PostFragment().newInstance(position)
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return dates[position]
    }
}