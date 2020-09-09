package com.example.myportfolio.frag

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.myportfolio.R

import com.example.myportfolio.adapters.forhomepage.HomeFragRVAdapter
import com.example.myportfolio.models.NewModel
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.homefrag.*
import okhttp3.*
import java.io.IOException


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {


	lateinit var recyclerList: RecyclerView


	lateinit var homeFragRVAdapter: HomeFragRVAdapter
	var newModel: NewModel? = null
	lateinit var mSwipeRefreshLayout: SwipeRefreshLayout


	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {


		val rootView = inflater.inflate(R.layout.homefrag, container, false)


		recyclerList = rootView.findViewById(R.id.home_news_recycler_list)
		recyclerList.setHasFixedSize(true)
		recyclerList.layoutManager = LinearLayoutManager(activity)

		mSwipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh) as SwipeRefreshLayout
//			mSwipeRefreshLayout.setOnRefreshListener(activity!!.applicationContext)
		mSwipeRefreshLayout.isRefreshing = true
		mSwipeRefreshLayout.setColorSchemeResources(
			R.color.colorPrimary,
			android.R.color.holo_green_dark,
			android.R.color.holo_orange_dark,
			android.R.color.holo_blue_dark
		)


		mSwipeRefreshLayout.setOnRefreshListener(OnRefreshListener {
			if (newModel != null) {
				homeFragRVAdapter = HomeFragRVAdapter(NewModel.getInstance()!!)
				recyclerList.adapter = homeFragRVAdapter
				Toast.makeText(activity!!.applicationContext, "Qayta yuklanmoqda!", Toast.LENGTH_LONG)
					.show()
			} else {


				NewModel.init(activity!!.applicationContext)
				Toast.makeText(activity!!.applicationContext, "Qayta yuklab bo'lmadi!", Toast.LENGTH_LONG)
					.show()
				Handler().postDelayed({ // Stop animation (This will be after 3 seconds)
					mSwipeRefreshLayout.isRefreshing = false
				}, 4000)
			}


		})

		newModel = NewModel.getInstance()
		if (newModel != null) {
			Handler().postDelayed({ // Stop animation (This will be after 3 seconds)
				mSwipeRefreshLayout.isRefreshing = false
				homeFragRVAdapter = HomeFragRVAdapter(newModel!!)
				recyclerList.adapter = homeFragRVAdapter

			}, 4000)
		} else {
			mSwipeRefreshLayout.isRefreshing = false

			Toast.makeText(
				activity!!.applicationContext,
				"Yuklab bo'lmadi. Internetni tekshirib qayta urunib ko'ring!",
				Toast.LENGTH_LONG
			)
				.show()
		}



		return rootView
	}

	private fun isNetworkAvailable(): Boolean {
		val connectivityManager =
			activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val activeNetworkInfo = connectivityManager.activeNetworkInfo
		return activeNetworkInfo != null && activeNetworkInfo.isConnected
	}


}
