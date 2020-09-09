package com.example.myportfolio.adapters.forlastnewspage

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myportfolio.R
import com.example.myportfolio.models.NewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_frag_item.view.*
import kotlinx.android.synthetic.main.last_news_frag_item.view.*

@Suppress("DEPRECATION")
class LastNewsRVAdapter(val newModel: NewModel) :
	RecyclerView.Adapter<LastNewsViewHolder>() {
	lateinit var context: Context

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastNewsViewHolder {
		context = parent.context
		val itemRootView: View = LayoutInflater.from(parent.context).inflate(
			R.layout.last_news_frag_item, parent, false
		)

		return LastNewsViewHolder(itemRootView);
	}


	override fun getItemCount(): Int {

		return newModel.lastnewsuz.size
	}

	override fun onBindViewHolder(holder: LastNewsViewHolder, position: Int) {

		holder.itemView.last_news_title.text = newModel.lastnewsuz[position].title
		holder.itemView.last_news_author.text = newModel.lastnewsuz[position].source
		val day = newModel.lastnewsuz[position].publishedAt.subSequence(0, 10) as String
		val date = newModel.lastnewsuz[position].publishedAt.subSequence(11, 16) as String

		holder.itemView.last_news_published_at.text = day + " " + date


	//	holder.itemView.last_news_description.text = newModel.articles[position].description
		val myUrl = newModel.lastnewsuz[position].url
		holder.itemView.last_news_description_full.text =
			Html.fromHtml("<a href=\"$myUrl\">Batafsil</a>")
		holder.itemView.last_news_description_full.setOnClickListener {

			val intent = Intent(Intent.ACTION_VIEW)
			intent.data = Uri.parse(myUrl)
			context.startActivity(intent)


		}

		Picasso.get()
			.load(newModel.lastnewsuz[position].urlToImage)
			.networkPolicy(NetworkPolicy.OFFLINE)
			.placeholder(R.drawable.download_icon)
			.into(holder.itemView.last_news_image_view, object : Callback {
				override fun onSuccess() {

				}

				override fun onError(e: Exception?) {
					Picasso.get()
						.load(newModel.lastnewsuz[position].urlToImage)
						.placeholder(R.drawable.download_icon)
						.into(holder.itemView.last_news_image_view)
				}
			});


	}
}


class LastNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
}
