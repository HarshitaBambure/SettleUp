package com.example.settleup.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.settleup.GroupDetailsActivity
import com.example.settleup.R
import com.example.settleup.helper.Constants
import com.example.settleup.ui.adapters.GroupAdapter
import com.example.settleup.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_mygroups.view.*

class MyGroupsFragment : Fragment() {

    private lateinit var adapter: GroupAdapter
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_mygroups, container, false)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        adapter = GroupAdapter()
        root.rv_groups.adapter = adapter
        adapter.onclick = {
            val intent = Intent(activity, GroupDetailsActivity::class.java)
            intent.putExtra(Constants.KEY_GRP_ID, it.id)
            startActivity(intent)
        }
        lifecycleScope.launchWhenStarted {
            val data = viewModel.getGroupList()
            data?.let { adapter.setData(it) }
        }
        return root
    }
}
















