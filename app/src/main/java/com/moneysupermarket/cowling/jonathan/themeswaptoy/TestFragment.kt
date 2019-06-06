package com.moneysupermarket.cowling.jonathan.themeswaptoy


import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : Fragment() {

    private var oldWindowFlags: Int = 0
    private var oldStatusBarColor: Int = 0

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

        requireActivity().window.apply {
            oldStatusBarColor = statusBarColor
            oldWindowFlags = attributes.flags


            statusBarColor = Color.TRANSPARENT
            addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        }

        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    teardown()
                    findNavController().popBackStack()
                }
            }
        )

        return inflater
            .cloneInContext(ContextThemeWrapper(requireContext(), theme))
            .inflate(R.layout.fragment_test, container, false)
    }

    private fun teardown() {
        requireActivity().window.apply {
            statusBarColor = oldStatusBarColor
            setFlags(oldWindowFlags, -1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            teardown()
            findNavController().navigate(R.id.action_testFragment_to_test2)
        }
    }
}
