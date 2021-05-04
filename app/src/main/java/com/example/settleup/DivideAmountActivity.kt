package com.example.settleup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.settleup.ui.adapters.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_divide_amount.*

class DivideAmountActivity : AppCompatActivity() {
    var list = ArrayList<Data>()
    lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_divide_amount)

        setupdata()

        adapter = RecyclerViewAdapter()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

    }

    private fun setupdata() {
        for (i in 1..10){
            var data = Data(i)
            list.add(data)
        }
        adapter.updateRecyclerData(list)
    }
}