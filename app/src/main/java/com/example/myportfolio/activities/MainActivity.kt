package com.example.myportfolio.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myportfolio.R
import com.example.myportfolio.adapters.NavigationRVAdapter
import com.example.myportfolio.frag.*
import com.example.myportfolio.listener.ClickListener
import com.example.myportfolio.listener.RecyclerTouchListener
import com.example.myportfolio.models.NavigationItemModel
import com.example.myportfolio.models.NewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	lateinit var drawerLayout: DrawerLayout
	private lateinit var adapter: NavigationRVAdapter

	private var items = arrayListOf(
		NavigationItemModel(R.drawable.ic_home, "Asosiy yangiliklar"),
		NavigationItemModel(R.drawable.ic_last_news, "So'nggi yangiliklar"),
		NavigationItemModel(R.drawable.ic_world, "Dunyo yangiliklar"),
		NavigationItemModel(R.drawable.ic_sport, "Sport yangiliklar"),
		NavigationItemModel(R.drawable.ic_neighborhood, "Mahalliy yangiliklar"),
		NavigationItemModel(R.drawable.ic_videonews, "Video yangiliklar")

	)

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)


		setContentView(R.layout.activity_main)

		setSupportActionBar(activity_main_toolbar)
		supportActionBar!!.title = ""




		val i: Int = intent.getIntExtra("video_back", 0)
		if (i == 1) {
			loadVideoNewsFrag()
		} else {


			loadHomeFragment()
		}
		drawerLayout = findViewById(R.id.drawer_layout)


		navigation_rv.layoutManager = LinearLayoutManager(this)
		navigation_rv.setHasFixedSize(true)

		// Add Item Touch Listener
		navigation_rv.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
			override fun onClick(view: View, position: Int) {
				when (position) {
					0 -> {
						loadHomeFragment()
					}
					1 -> {
						val bundle = Bundle()

						bundle.putString("fragmentLastNewsName", "LastNews Fragment")
						activity_main_toolbar_title.text = items[1].title
						val lastNewsFragment = LastNewsFragment()
						lastNewsFragment.arguments = bundle
						supportFragmentManager.beginTransaction()
							.replace(R.id.activity_main_content_id, lastNewsFragment).commit()
					}
					2 -> {

						val bundle = Bundle()

						bundle.putString("fragmentWorldNewsName", "WorldNews Fragment")
						activity_main_toolbar_title.text = items[2].title
						val worldNewsFragment = WorldNewsFragment()
						worldNewsFragment.arguments = bundle
						supportFragmentManager.beginTransaction()
							.replace(R.id.activity_main_content_id, worldNewsFragment).commit()
					}
					3 -> {
						val bundle = Bundle()
						bundle.putString("fragmentSportName", "Sport Fragment")

						activity_main_toolbar_title.text = items[3].title

						val locallyFrag = SportNewsFragment()
						locallyFrag.arguments = bundle
						supportFragmentManager.beginTransaction()
							.replace(R.id.activity_main_content_id, locallyFrag).commit()
					}
					4 -> {
						val bundle = Bundle()
						bundle.putString("fragmentLocalName", "Locally Fragment")

						activity_main_toolbar_title.text = items[4].title
						val locallyNewsFragment = LocallyNewsFragment()
//						locallyNewsFragment.arguments = bundle
						supportFragmentManager.beginTransaction()
							.replace(R.id.activity_main_content_id, locallyNewsFragment).commit()
					}
					5 -> {
		loadVideoNewsFrag()
//
//						val intent = Intent(applicationContext, VideoActivity::class.java)
//						startActivity(intent)
//						finish()
					}


				}


				// Don't highlight the 'Profile' and 'Like us on Facebook' item row
				if (position != 6) {
					updateAdapter(position)
				}
				Handler().postDelayed({
					drawerLayout.closeDrawer(GravityCompat.START)
				}, 200)
			}
		}))

		updateAdapter(0)


		val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
			this,
			drawerLayout, activity_main_toolbar,
			R.string.navigation_drawer_open,
			R.string.navigation_drawer_close
		) {
			override fun onDrawerClosed(drawerView: View) {
				// Triggered once the drawer closes
				super.onDrawerClosed(drawerView)
				try {
					val inputMethodManager =
						getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
					inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
				} catch (e: Exception) {
					e.stackTrace
				}
			}

			override fun onDrawerOpened(drawerView: View) {
				// Triggered once the drawer opens
				super.onDrawerOpened(drawerView)
				try {
					val inputMethodManager =
						getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
					inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
				} catch (e: Exception) {
					e.stackTrace
				}
			}

		}
		drawerLayout.addDrawerListener(toggle)

		toggle.syncState()


	}

	private fun loadVideoNewsFrag() {
		activity_main_toolbar_title.text = items[5].title
		val videoNewsFragment = VideoNewsFragment()
//						videoNewsFragment.arguments = bundle
		supportFragmentManager.beginTransaction()
			.replace(R.id.activity_main_content_id, videoNewsFragment).commit()

	}


	private fun loadHomeFragment() {

		val bundle = Bundle()
		bundle.putString("fragmentHomeName", "Home Fragment")
		activity_main_toolbar_title.text = items[0].title
		val homeFragment = HomeFragment()
		homeFragment.arguments = bundle
		supportFragmentManager.beginTransaction()
			.replace(R.id.activity_main_content_id, homeFragment).commit()
	}

	private fun updateAdapter(highlightItemPos: Int) {
		adapter = NavigationRVAdapter(items, highlightItemPos)
		navigation_rv.adapter = adapter
		adapter.notifyDataSetChanged()
	}

	override fun onBackPressed() {
		if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
			drawerLayout.closeDrawer(GravityCompat.START)
		} else {
			// Checking for fragment count on back stack
			if (supportFragmentManager.backStackEntryCount > 0) {
				// Go to the previous fragment
				supportFragmentManager.popBackStack()
			} else {
				// Exit the app
				super.onBackPressed()
			}
		}
	}
}
