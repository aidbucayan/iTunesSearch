package com.adrian.bucayan.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.support.v4.content.res.ResourcesCompat
import android.text.Editable
import android.text.Html
import android.text.Spanned
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * @author Adrian Bucayan
 */

class Utils @Inject constructor(private val context: Context) {

    fun getScreenWidth(): Int {
        return context.resources.displayMetrics.widthPixels
    }

    fun getScreenHeight(): Int {
        return context.resources.displayMetrics.heightPixels
    }

    fun isEmailValid(email: Editable): Boolean {
        val expression = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun getColor(resource: Int): Int {
        return ResourcesCompat.getColor(context.resources, resource, null)
    }

    fun getDrawable(context: Context, resource: Int): Drawable {
        return ResourcesCompat.getDrawable(context.resources, resource, null)!!
    }

    fun convertStringLocalTime(datetime: String, sourceFormat: String, outputFormat: String): String {
        val formatter = DateTimeFormat.forPattern(sourceFormat)
        val localTime = DateTime.parse(datetime, formatter.withZone(DateTimeZone.getDefault()))

        return localTime.toString(outputFormat)
    }

    fun convertToJodaDateTime(datetime: String, sourceFormat: String): DateTime {
        val formatter = DateTimeFormat.forPattern(sourceFormat)
        return DateTime.parse(datetime, formatter)
    }

    fun convertToJodaDateTimeWithZone(datetime: String, sourceFormat: String): DateTime {
        val formatter = DateTimeFormat.forPattern(sourceFormat)
        return DateTime.parse(datetime, formatter.withZone(DateTimeZone.forID("Asia/Singapore")))
    }

    fun convertToJodaDateTimeX(datetime: String, sourceFormat: String): DateTime {
        val formatter = DateTimeFormat.forPattern(sourceFormat)
        return DateTime.parse(datetime, formatter)
    }

    fun convertToJodaLocalTime(time: String, sourceFormat: String, outputFormat: String): LocalTime {
        val formatter = DateTimeFormat.forPattern(sourceFormat)
        val result = LocalTime.parse(time, formatter)
        return LocalTime.parse(result.toString(sourceFormat), DateTimeFormat.forPattern(outputFormat))
    }

    fun dip(int: Int): Int {
        return (int * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
    }

    fun getDisplayMetrics() : DisplayMetrics {
        return Resources.getSystem().displayMetrics
    }

    fun appVersion() : String {
        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return 0.0.toString()

    }

    fun appBuild() : Int {
        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return pInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return 0
    }

    fun isDeviceLocationEnabled(): Boolean {
        val service = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return service.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    @SuppressWarnings("deprecation")
    fun fromHtml(html: String) : Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    fun expand(v: View) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targetHeight = v.measuredHeight

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.layoutParams.height = 1
        v.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.height = if (interpolatedTime == 1f)
                    ViewGroup.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = (targetHeight / v.context.resources.displayMetrics.density).toLong()
        v.startAnimation(a)
    }

    fun collapse(v: View) {
        val initialHeight = v.measuredHeight

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = (initialHeight / v.context.resources.displayMetrics.density).toLong()
        v.startAnimation(a)
    }

    fun stripHtml(html: String): String {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(html).toString()
        }
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }


}