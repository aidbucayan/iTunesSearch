package com.adrian.bucayan.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem

import com.adrian.bucayan.R
import com.adrian.bucayan.ui.fragments.MainFragment

/**
 * @author Adrian Bucayan
 */

class MainActivity : BaseActivity() {

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null)
            supportActionBar!!.hide()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.activity_container, MainFragment(), this.toString())
                .commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                hideKeyboard()
                this.onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}
