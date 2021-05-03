package com.example.settleup
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.settleup.helper.Constants
import com.example.settleup.ui.CreateGroupActivity
import com.example.settleup.ui.fragments.HomeFragment
import com.example.settleup.ui.fragments.MyGroupsFragment
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var firebaseAuth: FirebaseAuth
    var isAllFabsVisible: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        supportActionBar?.hide()

       // txt_username.text = getPreferances(Constants.PREF_KEY_USERNAME)
        //txt_email.text = getPreferances(Constants.PREF_KEY_EMAIL)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab_action:ExtendedFloatingActionButton = findViewById(R.id.fab_action)
        val fab:FloatingActionButton = findViewById(R.id.fab)
        val fab_expenses:FloatingActionButton = findViewById(R.id.fab_expenses)

        val text_fab = findViewById<TextView>(R.id.txt_fab)
        val text_expenses = findViewById<TextView>(R.id.txt_expenses)

        fab.setVisibility(View.GONE)
        fab_expenses.setVisibility(View.GONE)
        text_fab.setVisibility(View.GONE)
        text_expenses.setVisibility(View.GONE)

        isAllFabsVisible = false
        fab_action.shrink()

        fab_action.setOnClickListener(
                View.OnClickListener {
                    isAllFabsVisible = if (!isAllFabsVisible!!) {

                        // when isAllFabsVisible becomes
                        // true make all the action name
                        // texts and FABs VISIBLE.
                        fab.show()
                        fab_expenses.show()
                        text_fab.setVisibility(View.VISIBLE)
                        text_expenses.setVisibility(View.VISIBLE)

                        // Now extend the parent FAB, as
                        // user clicks on the shrinked
                        // parent FAB
                        fab_action.extend()

                        // make the boolean variable true as
                        // we have set the sub FABs
                        // visibility to GONE
                        true
                    } else {

                        // when isAllFabsVisible becomes
                        // true make all the action name
                        // texts and FABs GONE.
                        fab.hide()
                        fab_expenses.hide()
                        text_fab.setVisibility(View.GONE)
                        text_expenses.setVisibility(View.GONE)

                        // Set the FAB to shrink after user
                        // closes all the sub FABs
                        fab_action.shrink()

                        // make the boolean variable false
                        // as we have set the sub FABs
                        // visibility to GONE
                        false
                    }
                })




        fab.setOnClickListener {
            val intent = Intent(this, CreateGroupActivity::class.java)
            startActivity(intent)
        }


        fab_expenses.setOnClickListener {
            val intent = Intent(this, NewExpenseActivity::class.java)
            startActivity(intent)
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

    private fun replaceFragment(fragment: Fragment, teg: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_framelayout, fragment, teg)
        transaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {
                val fragment = HomeFragment()
                replaceFragment(fragment, "Home")
            }

            R.id.nav_mygroup -> {
                val fragment = MyGroupsFragment()
                replaceFragment(fragment, "My Group")
            }
            R.id.nav_sendfeedback -> {
                // val fragment=  MyGroupsFragment()
                //replaceFragment(fragment,"My Group")
                //open gmail here
            }
            R.id.nav_logout -> {
                onLogout()
            }
            R.id.nav_aboutus -> {
                val fragment = AboutUsFragment()
                replaceFragment(fragment, "My Group")
            }
        }

    return true
    }
    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser == null){
            startActivity(Intent(this, SignInActivity::class.java))
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