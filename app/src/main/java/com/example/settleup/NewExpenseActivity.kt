package com.example.settleup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.settleup.db.entity.Expense
import com.example.settleup.ui.viewmodel.ExpenseViewModel
import kotlinx.android.synthetic.main.activity_new_expense.*

class NewExpenseActivity : AppCompatActivity() {

    private lateinit var ViewModel : ExpenseViewModel
    val users = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_expense)

        ViewModel = ViewModelProviders.of(this).get(ExpenseViewModel::class.java)
        lifecycleScope.launchWhenStarted {
         val userData=   ViewModel.getMembersbyGroupid(1)
            userData?.forEach {
                users.add(it.toString())
            }
            val arrayAdapter = ArrayAdapter(this@NewExpenseActivity,android.R.layout.simple_spinner_dropdown_item,users)
            spinner_paid.adapter = arrayAdapter
        }


        spinner_paid.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                if(selectedItem == "Add new category") {
                    // do your stuff
                }
            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        btn_next.setOnClickListener {
             if (TextUtils.isEmpty(edt_amount.text.toString())) {
                Toast.makeText(this, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Proceed..", Toast.LENGTH_SHORT).show()
                 val intent= Intent(this,DivideAmountActivity::class.java)
                 startActivity(intent)

             }

        }

    }
    fun insertExpense(Expense: Expense){
       ViewModel.insertExpense(Expense)
    }
}