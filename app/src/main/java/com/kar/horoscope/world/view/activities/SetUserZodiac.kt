package com.kar.horoscope.world.view.activities

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.NumberPicker
import com.kar.horoscope.world.R
import com.kar.horoscope.world.models.Preference
import com.kar.horoscope.world.repository.SharedPrefPreferenceService
import com.kar.horoscope.world.util.ChangeDividerColor
import com.kar.horoscope.world.viewmodels.setuserzodiac.SetUserZodiacViewModel
import com.kar.horoscope.world.viewmodels.setuserzodiac.SetUserZodiacViewModelFactory
import kotlinx.android.synthetic.main.activity_set_user_zodiac.*

class SetUserZodiac : AppCompatActivity() {

    lateinit var viewModel : SetUserZodiacViewModel
    lateinit var sharedPreferences : SharedPreferences
    lateinit var sharedPrefPreferenceService: SharedPrefPreferenceService
    var selectedItem : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_user_zodiac)


        sharedPreferences = getSharedPreferences( "Preference", Context.MODE_PRIVATE )
        sharedPrefPreferenceService = SharedPrefPreferenceService(sharedPreferences)

        val vm : SetUserZodiacViewModel by lazy {
            ViewModelProviders.of(this,
                SetUserZodiacViewModelFactory(
                    sharedPrefPreferenceService
                )
            ).get(
                SetUserZodiacViewModel::class.java)
        }
        viewModel = vm


        val names = resources.getStringArray(R.array.Zodiacs)
        numberPicker.minValue = 0
        numberPicker.maxValue = names.size-1
        numberPicker.displayedValues = names
        numberPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        numberPicker.wrapSelectorWheel = false
        ChangeDividerColor().changeDividerColor( numberPicker, Color.TRANSPARENT )

        numberPicker.setOnValueChangedListener { numberPicker, _, _ ->
            selectedItem = numberPicker.value
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        startActivity(Intent ( this, MainActivity::class.java ) )
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.set, menu)
        return true
    }

    @SuppressLint("CheckResult")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if( item.itemId == R.id.set ) {

            val preference = Preference ( "Name", selectedItem )
            viewModel.saveTheValue( preference )

            val intent = Intent ( this, Forecast::class.java )
            intent.putExtra("Title", viewModel.getTheValue("Name", -1 ) )
            startActivity(intent )
            finish()

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
