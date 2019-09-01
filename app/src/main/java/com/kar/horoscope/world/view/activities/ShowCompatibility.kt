package com.kar.horoscope.world.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.codesgood.views.JustifiedTextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.kar.horoscope.world.R
import com.kar.horoscope.world.repository.ShowRepository
import com.kar.horoscope.world.viewmodels.showcompatibility.ShowCompatibilityVMFactory
import com.kar.horoscope.world.viewmodels.showcompatibility.ShowCompatibilityViewModel
import kotlinx.android.synthetic.main.activity_show_compatibility.*

class ShowCompatibility : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_compatibility)

        title = "Compatibility"
        val intent = intent

        val male = intent.getStringExtra("MaleName" )
        val female = intent.getStringExtra("FemaleName" )

        val bitmapMale = intent.getParcelableExtra<Bitmap>("MaleBitmap")
        val bitmapFemale = intent.getParcelableExtra<Bitmap>("FemaleBitmap")

        maleZodiac.text = male
        femaleZodiac.text = female

        designText(maleZodiac)
        designText(femaleZodiac)

        maleImage.setImageBitmap(bitmapMale)
        femaleImage.setImageBitmap(bitmapFemale)

        val pair = "${male.toLowerCase()}-${female.toLowerCase()}"
        val repository = ShowRepository()

        val viewModel: ShowCompatibilityViewModel by lazy {
            ViewModelProviders.of(this,
                ShowCompatibilityVMFactory(repository)
            ).get(ShowCompatibilityViewModel::class.java)
        }

        val compatibilityText = findViewById<JustifiedTextView>(R.id.compText)
        viewModel.getText(pair).subscribe {
            compatibilityText.text = it.text
        }
    }

    private fun designText( txt: TextView) {
        txt.setTextColor(Color.WHITE)
        txt.setTypeface(txt.typeface, Typeface.BOLD_ITALIC )
        txt.textSize = 18.toFloat()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity( Intent ( this, MainActivity::class.java) )
        finish()
    }

}
