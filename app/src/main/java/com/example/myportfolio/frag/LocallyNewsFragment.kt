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
import com.example.myportfolio.adapters.forlastnewspage.LastNewsRVAdapter
import com.example.myportfolio.adapters.forlocallynews.LocallyNewsRVAdapter
import com.example.myportfolio.models.NewModel
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.locally_news.view.*
import okhttp3.*
import java.io.IOException

class LocallyNewsFragment : Fragment() {
	lateinit var locallyNewsList: RecyclerView

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {


		(activity as MainActivity).activity_main_toolbar_title!!.text = ("Mahalliy yangiliklari")

		val rootView = inflater.inflate(R.layout.locally_news, container, false)


		locallyNewsList = rootView.findViewById(R.id.locally_news_list)
		locallyNewsList.setHasFixedSize(true)
		locallyNewsList.layoutManager = LinearLayoutManager(activity)

		var newModel = NewModel.getInstance()
		if (newModel != null) {
			Handler().postDelayed({ // Stop animation (This will be after 3 seconds)

				locallyNewsList.adapter = LocallyNewsRVAdapter(newModel)

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


}
