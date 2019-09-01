package com.kar.horoscope.world.repository

import android.content.Context
import com.kar.horoscope.world.R
import com.kar.horoscope.world.service.CompatibilityService

class CompatibilityRepository( private val context: Context ) : CompatibilityService {
    override fun getNames() : Array<String>{
        return context.resources.getStringArray( R.array.Zodiacs )
    }

    override fun getImages() : Array<Int>{
        return arrayOf(
            R.drawable.logo_aries,
            R.drawable.logo_taurus,
            R.drawable.logo_gemini,
            R.drawable.logo_cancer,
            R.drawable.logo_leo,
            R.drawable.logo_virgo,
            R.drawable.logo_libra,
            R.drawable.logo_scorpio,
            R.drawable.logo_sagittarius,
            R.drawable.logo_capricorn,
            R.drawable.logo_aquarius,
            R.drawable.logo_pisces )

    }

}