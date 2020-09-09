package com.example.myportfolio.frag

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myportfolio.R
import com.example.myportfolio.activities.MainActivity
import com.example.myportfolio.activities.VideoActivity
import com.example.myportfolio.adapters.YoutubeVideoAdapter
import com.example.myportfolio.listener.ClickListener
import com.example.myportfolio.listener.RecyclerTouchListener
import com.example.myportfolio.models.YoutubeVideoModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class VideoNewsFragment : Fragment() {

	lateinit var videoNewsList: RecyclerView

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		(activity as MainActivity).activity_main_toolbar_title!!.text = ("Video yangiliklari")


		val rootView = inflater.inflate(R.layout.video_news, container, false)
		videoNewsList = rootView.findViewById(R.id.video_news_list)
		videoNewsList.setHasFixedSize(true)
		videoNewsList.layoutManager = LinearLayoutManager(activity!!.applicationContext)

		populateRecyclerView()

		return rootView

	}


	private fun populateRecyclerView() {
		val youtubeVideoModelArrayList = generateDummyVideoList()
		val adapter = YoutubeVideoAdapter(activity!!.applicationContext, youtubeVideoModelArrayList)
		videoNewsList.adapter = adapter



		videoNewsList.addOnItemTouchListener(
			RecyclerTouchListener(
				activity!!.applicationContext,
				object : ClickListener {
					override fun onClick(view: View, position: Int) {
						val intent =
							Intent(activity!!.applicationContext, VideoActivity::class.java)
						intent.putExtra("video_id", youtubeVideoModelArrayList[position].videoId)
						startActivity(intent)
						activity!!.finish()
					}


				})
		)

	}




	private fun generateDummyVideoList(): ArrayList<YoutubeVideoModel> {
		val youtubeVideoModelArrayList = ArrayList<YoutubeVideoModel>()

		//get the video id array, title array and duration array from strings.xml
		val videoIDArray = resources.getStringArray(R.array.video_id_array)
		val videoTitleArray = resources.getStringArray(R.array.video_title_array)
		val videoDurationArray = resources.getStringArray(R.array.video_duration_array);

		//loop through all items and add them to arraylist
		for (i in videoIDArray.indices) {
			val youtubeVideoModel = YoutubeVideoModel()
			youtubeVideoModel.videoId = videoIDArray[i]
			youtubeVideoModel.title = videoTitleArray[i]
			youtubeVideoModel.duration = videoDurationArray[i];
			youtubeVideoModelArrayList.add(youtubeVideoModel)
		}
		return youtubeVideoModelArrayList
	}

}
