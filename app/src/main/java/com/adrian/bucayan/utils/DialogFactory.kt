package com.adrian.bucayan.utils

import android.app.Dialog
import android.content.Context
import com.adrian.bucayan.R
import com.afollestad.materialdialogs.GravityEnum
import com.afollestad.materialdialogs.MaterialDialog
import java.util.*


class DialogFactory {

    companion object {

        fun showInfoDialog(context: Context, title: String, message: String): Dialog {
            return showInfoDialog(
                context,
                title,
                message,
                MaterialDialog.SingleButtonCallback { materialDialog, _ -> materialDialog.dismiss() })
        }

        fun showInfoDialog(
            context: Context,
            title: String,
            message: String,
            callback: MaterialDialog.SingleButtonCallback
        ): Dialog {
            return MaterialDialog.Builder(context)
                .backgroundColorRes(R.color.white)
                .cancelable(false)
                .title(title)
                .titleColorRes(R.color.black)
                .titleGravity(GravityEnum.CENTER)
                .content(message)
                .contentColorRes(R.color.black)
                .contentGravity(GravityEnum.CENTER)
                .positiveText(android.R.string.ok)
                .positiveColorRes(R.color.colorAccent)
                .onPositive(callback)
                .show()
        }

        fun showCountry(context: Context, title: String, callback: MaterialDialog.ListCallback): Dialog {

            val countryList = convertCountryListToStrings()

            return MaterialDialog.Builder(context)
                .title(title)
                .titleColorRes(R.color.black)
                .titleGravity(GravityEnum.CENTER)
                .items(*countryList)
                .itemsColorRes(R.color.black)
                .itemsGravity(GravityEnum.CENTER)
                .itemsCallback(callback)
                .show()
        }

        private fun convertCountryListToStrings(): Array<String> {
            val mCountry = ArrayList<String>()

            val isoCountries: Array<String> = Locale.getISOCountries()
            for (country in isoCountries) {
                val locale = Locale("en", country)
                val iso: String = locale.isO3Country
                val code: String = locale.country
                val name: String = locale.displayCountry
                if ("" != iso && "" != code && "" != name) {
                    mCountry.add(name)
                }
            }

            return mCountry.toTypedArray()
        }

       fun showMedia(context: Context, title: String, callback: MaterialDialog.ListCallback): Dialog {

           val mediaList = context.resources.getStringArray(R.array.media_list)

            return MaterialDialog.Builder(context)
                .title(title)
                .titleColorRes(R.color.black)
                .titleGravity(GravityEnum.CENTER)
                .items(*mediaList)
                .itemsColorRes(R.color.black)
                .itemsGravity(GravityEnum.CENTER)
                .itemsCallback(callback)
                .show()
        }

    }


}