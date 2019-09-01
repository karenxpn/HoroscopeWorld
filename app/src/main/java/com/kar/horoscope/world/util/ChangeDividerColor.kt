package com.kar.horoscope.world.util

import android.graphics.drawable.ColorDrawable
import android.widget.NumberPicker


class ChangeDividerColor {
    fun changeDividerColor(picker : NumberPicker, color : Int ) {
        
        try {
            val mField = NumberPicker::class.java.getDeclaredField("mSelectionDivider")
            mField.isAccessible = true
            val colorDrawable = ColorDrawable(color)
            mField.set(picker, colorDrawable)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}