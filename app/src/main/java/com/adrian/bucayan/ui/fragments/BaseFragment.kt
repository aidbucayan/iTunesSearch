package com.adrian.bucayan.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.adrian.bucayan.R
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment()  {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * This function is for navigation of different fragments
     */
    fun changeFragment(
        fragment: Fragment,
        tag: String,
        bundle: Bundle
    ) {
        if (fragmentManager?.findFragmentByTag(tag) == null) {
            fragment.arguments = bundle
            fragmentManager?.beginTransaction()?.run {
                replace(R.id.activity_container, fragment, tag)
                addToBackStack(null)
                commit()
            }
        } else {
            if (!fragment.isVisible) {
                fragmentManager?.popBackStack()
            }
        }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

}
