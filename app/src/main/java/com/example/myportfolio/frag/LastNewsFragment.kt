package com.example.myportfolio.frag

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myportfolio.activities.MainActivity
import com.example.myportfolio.R
import com.example.myportfolio.adapters.forhomepage.HomeFragRVAdapter
import com.example.myportfolio.adapters.forlastnewspage.LastNewsRVAdapter
import com.example.myportfolio.models.NewModel
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

@Suppress("DEPRECATION")
class LastNewsFragment : Fragment() {


	lateinit var lastNewsList: RecyclerView
	lateinit var lastNewsRVAdapter: LastNewsRVAdapter
	var newModel: NewModel? = null
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {

		(activity as MainActivity).activity_main_toolbar_title!!.text = ("So'nggi yangiliklari")

		val rootView = inflater.inflate(R.layout.last_news_frag, container, false)

		lastNewsList = rootView.findViewById(R.id.last_news_list)
		lastNewsList.setHasFixedSize(true)
		lastNewsList.layoutManager = LinearLayoutManager(activity)


		newModel = NewModel.getInstance()
		if (newModel != null) {
			Handler().postDelayed({ // Stop animation (This will be after 3 seconds)
				lastNewsRVAdapter = LastNewsRVAdapter(newModel!!)
				lastNewsList.adapter = lastNewsRVAdapter

			}, 2000)
		} else {
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
