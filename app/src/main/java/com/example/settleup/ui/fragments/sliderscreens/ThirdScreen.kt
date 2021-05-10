package com.example.settleup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.settleup.helper.Constants.PREF_SETTLE_UP
import kotlinx.android.synthetic.main.fragment_third_screen.view.*


class ThirdScreen : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third_screen, container, false)

        view.finish.setOnClickListener {
            onBoardingFinished()
            activity?.finish()
            startActivity(Intent(context, SignInActivity::class.java))


        }

        return view
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences(PREF_SETTLE_UP, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}