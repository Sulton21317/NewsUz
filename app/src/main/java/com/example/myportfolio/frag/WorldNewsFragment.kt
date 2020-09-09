package com.example.myportfolio.frag

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myportfolio.R
import com.example.myportfolio.activities.MainActivity
import com.example.myportfolio.adapters.forworldnews.WorldNewsRVAdapter
import com.example.myportfolio.models.NewModel
import kotlinx.android.synthetic.main.activity_main.*

class WorldNewsFragment : Fragment() {
	//	override fun onCreate(savedInstanceState: Bundle?) {
//
//		super.onCreate(savedInstanceState)
//	}
	private lateinit var worldNewsList: RecyclerView

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		(activity as MainActivity).activity_main_toolbar_title!!.text = ("Dunyo yangiliklari")
		val rootView = inflater.inflate(R.layout.world_news, container, false)
		worldNewsList = rootView.findViewById(R.id.world_news_list)
		worldNewsList.setHasFixedSize(true)
		worldNewsList.layoutManager = LinearLayoutManager(activity!!.applicationContext)


		var newModel = NewModel.getInstance()
		if (newModel != null) {
			Handler().postDelayed({ // Stop animation (This will be after 3 seconds)

				worldNewsList.adapter = WorldNewsRVAdapter(newModel)

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
