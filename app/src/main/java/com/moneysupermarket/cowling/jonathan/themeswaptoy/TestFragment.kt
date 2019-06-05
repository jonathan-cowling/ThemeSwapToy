package com.moneysupermarket.cowling.jonathan.themeswaptoy


import android.os.Bundle
import android.preference.PreferenceManager
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val theme = when (PreferenceManager
            .getDefaultSharedPreferences(requireContext())
            .getString("theme", null)) {
            "red" -> R.style.AppTheme_Red
            "blue" -> R.style.AppTheme_Blue
            "green" -> R.style.AppTheme_Green
            else -> R.style.AppTheme_Red
        }

        return inflater
            .cloneInContext(ContextThemeWrapper(requireContext(), theme))
            .inflate(R.layout.fragment_test, container, false)
    }


}
