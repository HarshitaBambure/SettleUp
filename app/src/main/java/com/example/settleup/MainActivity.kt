package com.example.settleup


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.settleup.ui.fragments.HomeFragment
import com.example.settleup.ui.fragments.MyGroupsFragment
import com.example.settleup.ui.fragments.NewGroupFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        supportActionBar?.hide()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
       // val navController = findNavController(R.id.content_framelayout)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.nav_logout, R.id.nav_newgroup, R.id.nav_sendfeedback
//            ), drawerLayout
//        )
//       // setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener(this)


    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.content_framelayout)

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun replaceFragment(fragment: Fragment,teg:String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_framelayout, fragment,teg)
        transaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home ->{
                val fragment=  HomeFragment()
                replaceFragment(fragment,"New Group")
            }
            R.id.nav_newgroup ->{
                val fragment=  NewGroupFragment()
                replaceFragment(fragment,"New Group")
            }
            R.id.nav_mygroup ->{
                val fragment=  MyGroupsFragment()
                replaceFragment(fragment,"My Group")
            }
            R.id.nav_sendfeedback ->{
               // val fragment=  MyGroupsFragment()
                //replaceFragment(fragment,"My Group")
                //open gmail here
            }
            R.id.nav_logout ->{
                onLogout()
            }
            R.id.nav_aboutus ->{

            }
        }

    return true
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
                   }
    }
    fun onLogout(){
         firebaseAuth.signOut()
        checkUser()
    }
}