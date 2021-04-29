package com.example.settleup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.settleup.databinding.ActivityProfileBinding
import com.example.settleup.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handel click, logout user
        binding.btnLogout.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser == null){
            startActivity(Intent(this,SignInActivity::class.java))
            finish()
        }
        else{
            //user logged in
            val email = firebaseUser.email
            binding.emailTv.text = email
        }
    }

    // logout code
    fun onLogout(){
        firebaseAuth.signOut()
        checkUser()
    }
}