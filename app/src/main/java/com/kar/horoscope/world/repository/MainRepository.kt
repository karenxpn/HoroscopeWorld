package com.kar.horoscope.world.repository

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.kar.horoscope.world.R
import com.kar.horoscope.world.models.MainViewZodiacModel
import com.kar.horoscope.world.models.Preference
import com.kar.horoscope.world.service.MainService
import com.kar.horoscope.world.view.LogIn
import com.kar.horoscope.world.view.activities.About
import com.kar.horoscope.world.view.activities.Compatibility
import com.kar.horoscope.world.view.activities.SetUserZodiac
import io.reactivex.Observable


class MainRepository(private val context: Context) : MainService {

    override fun logOut() {
        AuthUI.getInstance()
                .signOut(context)
                .addOnCompleteListener {
                    val preference = context.getSharedPreferences("Preference", Context.MODE_PRIVATE)
                    val sharedPrefPreferenceService = SharedPrefPreferenceService(preference)

                    sharedPrefPreferenceService.savePreference( Preference("Name", -1 ) )
                    context.startActivity( Intent( context, LogIn::class.java ) )
                    (context as Activity).overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    context.finish()
                }
    }

    override fun goToCompatibility() {
        context.startActivity(Intent ( context, Compatibility::class.java ) )
        (context as Activity).overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        context.finish()
    }

    override fun goToSetDefault() {
        context.startActivity( Intent( context, SetUserZodiac::class.java ) )
        (context as Activity).overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        context.finish()
    }

    override fun goToAbout() {
        context.startActivity( Intent( context, About::class.java ) )
        (context as Activity).overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        context.finish()
    }

    override fun sendEmail() {
        val uri = Uri.parse("mailto:karenmirakyan@gmail.com")

        val emailIntent = Intent(Intent.ACTION_SENDTO, uri)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here")

        try {
            context.startActivity(Intent.createChooser(emailIntent, "Send mail..."))
            Log.i("Finished sending", "")
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                context,
                "There is no email client installed.", Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun sendFeedback() {
        val uri = Uri.parse("market://details?id=" + context.packageName)
        val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
        try {
            context.startActivity( myAppLinkToMarket )
        } catch (e: ActivityNotFoundException) {
            Toast.makeText( context, " unable to find market app", Toast.LENGTH_LONG).show()
        }

    }


    override fun generateNames(): List<String> {
        return context.resources.getStringArray(R.array.Zodiacs).toList()
    }

    override fun generateImages(): List<Int> {

        return listOf(
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
            R.drawable.logo_pisces)
    }

    override fun generateModel(): Observable<List<MainViewZodiacModel>> {
        val names = generateNames()
        val images = generateImages()
        val array = ArrayList<MainViewZodiacModel>()

        for ( i in 0 until names.size) {
            array.add ( MainViewZodiacModel( names[i], images[i] ) )
        }

        return Observable.fromArray( array )
    }

}