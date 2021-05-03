package com.example.settleup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.settleup.databinding.ActivitySignInBinding
import com.example.settleup.db.entity.User
import com.example.settleup.helper.Constants
import com.example.settleup.ui.viewmodel.SignInViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var ViewModel : SignInViewModel
    private companion object{
        private const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)

             val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.signInButton.setOnClickListener{
            Log.d(TAG," onCreate: begin Google SignIn")
    val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
             }

        }


    fun insertUser(user: User){
        ViewModel.insertUser(user)
    }

    private fun checkUser() {
    //check user logged in or not
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            //user is already logged in
            //start profile activity
            finish()
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))

        }
        }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            Log.d(TAG,"onActivityResult: Google SignIn intent result ")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
              //  val account = accountTask.getResult(ApiException::class.java)
                val account = accountTask.getResult(ApiException::class.java)
                insertUser(user = User(1,
                    account?.email.toString(), account?.displayName.toString(), account?.idToken.toString()))
                 firebaseAuthWithGoogleAccount(account)
                savePreferances(Constants.PREF_KEY_USERNAME,account?.displayName.toString())
                savePreferances(Constants.PREF_KEY_EMAIL,account?.email.toString())
            }
            catch (e: Exception){
                Log.d(TAG, "onActivityResult: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
    Log.d(TAG,"firebaseAuthWithGoogleAccount: begin firebase auth with google account")

        val credential = GoogleAuthProvider.getCredential(account!!.idToken,null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->
                Log.d(TAG, "firebaseAuthWithGoogleAccount: LoggedIn")

                //get loggedIn user
                val firebaseUser = firebaseAuth.currentUser

                //get User info
                val uid = firebaseUser!!.uid
                val email = firebaseUser.email

                Log.d(TAG, "firebaseAuthWithGoogleAccount: Uid: $uid")
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Email : $email")
                 //check if user is new or exixting
                if(authResult.additionalUserInfo!!.isNewUser){
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Account Created... \n$email")
                    Toast.makeText(this@SignInActivity,"Account Created... \n$email",Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Existing user... \n$email")
                    Toast.makeText(this@SignInActivity,"LoggedIn... \n$email",Toast.LENGTH_SHORT).show()

                }
                //start profile activity
                startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                finish()
                }
            .addOnFailureListener{ e ->
                //login failed
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Loggin Failed due to ${e.message}")
                Toast.makeText(this@SignInActivity,"Loggin Failed due to ${e.message}",Toast.LENGTH_SHORT).show()


            }

    }

}