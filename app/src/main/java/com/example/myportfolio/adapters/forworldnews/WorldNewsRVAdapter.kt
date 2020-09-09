package com.example.myportfolio.adapters.forworldnews

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myportfolio.R
import com.example.myportfolio.adapters.forsportnews.SportNewsViewHolder
import com.example.myportfolio.models.NewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.locally_news_frag_item.view.*
import kotlinx.android.synthetic.main.sport_news_frag_item.view.*
import kotlinx.android.synthetic.main.world_news_frag_item.view.*

class WorldNewsRVAdapter(val newModel: NewModel) : RecyclerView.Adapter<WorldNewsViewHolder>() {
	lateinit var context: Context
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorldNewsViewHolder {
		context = parent.context
		val rootView = LayoutInflater.from(parent.context)
			.inflate(R.layout.world_news_frag_item, parent, false)


		return WorldNewsViewHolder(rootView)

	}



	override fun getItemCount(): Int {

		return newModel.worldnews.size

	}

	override fun onBindViewHolder(holder: WorldNewsViewHolder, position: Int) {
		holder.itemView.world_news_title.text = newModel.worldnews[position].title
		holder.itemView.world_news_source.text = newModel.worldnews[position].source
		val day = newModel.worldnews[position].publishedAt.subSequence(0, 10) as String
		val date = newModel.worldnews[position].publishedAt.subSequence(11, 16) as String

		holder.itemView.world_news_published_at.text = day + " " + date


		val myUrl = newModel.sportnews[position].url

		holder.itemView.setOnClickListener {

			val intent = Intent(Intent.ACTION_VIEW)
			intent.data = Uri.parse(myUrl)
			context.startActivity(intent)


		}
		Picasso.get()
			.load(newModel.worldnews[position].urlToImage)
			.networkPolicy(NetworkPolicy.OFFLINE)
			.placeholder(R.drawable.download_icon)
			.into(holder.itemView.world_news_image_view, object : Callback {
				override fun onSuccess() {

				}

				override fun onError(e: Exception?) {
					Picasso.get()
						.load(newModel.worldnews[position].urlToImage)
						.placeholder(R.drawable.download_icon)
						.into(holder.itemView.world_news_image_view)
				}
			});


	}


}

class WorldNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}