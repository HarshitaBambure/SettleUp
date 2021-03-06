package com.example.settleup.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.settleup.R
import com.example.settleup.ui.CreateGroupActivity
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    var isAllFabsVisible: Boolean? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root1view = inflater.inflate(R.layout.fragment_home, container, false)

        root1view.fab.setVisibility(View.GONE)
        //  root1view.fab_expenses.setVisibility(View.GONE)
        root1view.txt_fab.setVisibility(View.GONE)
        //  root1view.txt_expenses.setVisibility(View.GONE)

        isAllFabsVisible = false
        root1view.fab_action.shrink()

        root1view.fab_action.setOnClickListener(
                View.OnClickListener {
                    isAllFabsVisible = if (!isAllFabsVisible!!) {
                        root1view.fab.show()
                        // root1view.fab_expenses.show()
                        root1view.txt_fab.setVisibility(View.VISIBLE)
                        // root1view. txt_expenses.setVisibility(View.VISIBLE)
                        root1view.fab_action.extend()
                        true
                    } else {

                        root1view.fab.hide()
                        // root1view.fab_expenses.hide()
                        root1view.txt_fab.setVisibility(View.GONE)
                        //root1view. txt_expenses.setVisibility(View.GONE)
                        root1view.fab_action.shrink()
                        false
                    }
                })

        root1view.fab.setOnClickListener {
            val intent = Intent(activity, CreateGroupActivity::class.java)
            startActivity(intent)
        }

//          root1view.fab_expenses.setOnClickListener {
//              val intent = Intent(activity, NewExpenseActivity::class.java)
//              intent.putExtra(Constants.KEY_GRP_ID,grpId)
//              startActivity(intent)
//          }

        return root1view

    }

    private fun replaceFragment(fragment: Fragment, teg: String) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment, teg)
        transaction.commit()
    }

    override fun onResume() {
        super.onResume()

        replaceFragment(MyGroupsFragment(), "group_listing")
    }

}
