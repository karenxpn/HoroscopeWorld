package com.kar.horoscope.world.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.kar.horoscope.world.R
import com.kar.horoscope.world.databinding.ActivityCompatibilityBinding
import com.kar.horoscope.world.repository.CompatibilityRepository
import com.kar.horoscope.world.repository.adapter.CompatibilityAdapter
import com.kar.horoscope.world.service.ItemClickedCallback
import com.kar.horoscope.world.util.SpacesItemDecoration
import com.kar.horoscope.world.viewmodels.compatibility.CompatibilityVMFactory
import com.kar.horoscope.world.viewmodels.compatibility.CompatibilityViewModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_compatibility.*
import kotlin.math.roundToInt


class Compatibility : AppCompatActivity(), ItemClickedCallback {

    private var currentTag: String = "male"
    private lateinit var binding : ActivityCompatibilityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_compatibility )

        val repository = CompatibilityRepository(this )
        val viewModel: CompatibilityViewModel by lazy {
            ViewModelProviders.of(this,
                CompatibilityVMFactory(repository)
            ).get(CompatibilityViewModel::class.java)
        }

        recyclerView.addItemDecoration( SpacesItemDecoration( 100 ))
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = CompatibilityAdapter (
            viewModel.getImageData,
            viewModel.getNameData,
            this)

        binding.male.isClickable = true
        binding.female.isClickable = true

        changeSize( binding.male, 88 )
        setCurrentTag( "male" )

        binding.male.setOnClickListener {
            changeSize(binding.male, 88 )
            changeSize(binding.female, 77 )
            setCurrentTag("male")
        }

        binding.female.setOnClickListener {
            changeSize(binding.male, 77 )
            changeSize(binding.female, 88 )
            setCurrentTag("female")
        }

        binding.goCompatibility.setOnClickListener {
            if ( binding.genderMale.text.toString() == "Male" || binding.genderFemale.text.toString() == "Female" )
                Toast.makeText(this, "Select Zodiac", Toast.LENGTH_SHORT ).show()

            else {
                val intent = Intent( this, ShowCompatibility::class.java )
                intent.putExtra("MaleName", binding.genderMale.text.toString())
                intent.putExtra("FemaleName", binding.genderFemale.text.toString())

                binding.male.isDrawingCacheEnabled = true
                binding.female.isDrawingCacheEnabled = true

                val bitmapMale = binding.male.drawingCache
                val bitmapFemale = binding.female.drawingCache

                intent.putExtra("MaleBitmap", bitmapMale )
                intent.putExtra("FemaleBitmap", bitmapFemale )

                startActivity( intent )
                finish()

            }
        }
    }

    private fun setCurrentTag(tag: String) {
        this.currentTag = tag
    }

    private fun dpToPx(dp: Int): Int {
        val density = applicationContext.resources
            .displayMetrics
            .density
        return (dp.toFloat() * density).roundToInt()
    }

    private fun changeSize(imageView: CircleImageView, size: Int) {
        imageView.layoutParams.width = dpToPx(size)
        imageView.layoutParams.height = dpToPx(size)
        imageView.requestLayout()
    }

    override fun itemClicked(name: String, image: Int) {

        if(currentTag == "male") {
            binding.genderMale.text = name
            binding.genderMale.setTextColor(Color.GRAY )
            binding.genderMale.textSize = 18.toFloat()

            Glide.with(applicationContext).load(image).into(binding.male)
        }

        else if ( currentTag == "female" ){
            binding.genderFemale.text = name
            binding.genderFemale.setTextColor(Color.GRAY )
            binding.genderFemale.textSize = 18.toFloat()

            Glide.with(applicationContext).load(image).into(binding.female)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent ( this, MainActivity::class.java))
        finish()
    }
}
