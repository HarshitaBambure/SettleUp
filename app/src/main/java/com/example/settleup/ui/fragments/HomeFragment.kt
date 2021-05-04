package com.example.settleup.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.settleup.ui.adapters.MyPageAdapter
import com.example.settleup.R

class HomeFragment : Fragment() {

      override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val root1View =  inflater.inflate(R.layout.fragment_home, container, false)
          val fragmentAdapter =  MyPageAdapter(childFragmentManager)
        //  viewPager_home.adapter = fragmentAdapter

        //  tabLayout.setupWithViewPager(viewPager_home)
        return root1View

      }

}
