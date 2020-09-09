package com.example.myportfolio.adapters.forsportnews

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myportfolio.R
import com.example.myportfolio.models.NewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.locally_news_frag_item.view.*
import kotlinx.android.synthetic.main.sport_news_frag_item.view.*

@Suppress("DEPRECATION")
class SportNewsRVAdapter(val newModel: NewModel) : RecyclerView.Adapter<SportNewsViewHolder>() {

	lateinit var context: Context
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportNewsViewHolder {
		context = parent.context
		val rootView = LayoutInflater.from(parent.context)
			.inflate(R.layout.sport_news_frag_item, parent, false)

		return SportNewsViewHolder(rootView)
	}


	override fun getItemCount(): Int {

		return newModel.sportnews.size

	}

	override fun onBindViewHolder(holder: SportNewsViewHolder, position: Int) {
		holder.itemView.sport_news_title.text = newModel.sportnews[position].title
		holder.itemView.sport_news_author.text = newModel.sportnews[position].source
		val day = newModel.sportnews[position].publishedAt.subSequence(0, 10) as String
		val date = newModel.sportnews[position].publishedAt.subSequence(11, 16) as String

		holder.itemView.sport_news_published_at.text = day + " " + date


		val myUrl = newModel.sportnews[position].url
		holder.itemView.sport_news_description_full.text =
			Html.fromHtml("<a href=\"$myUrl\">Batafsil</a>")

		holder.itemView.sport_news_description_full.setOnClickListener {

			val intent = Intent(Intent.ACTION_VIEW)
			intent.data = Uri.parse(myUrl)
			context.startActivity(intent)


		}
		Picasso.get()
			.load(newModel.sportnews[position].urlToImage)
			.networkPolicy(NetworkPolicy.OFFLINE)
			.placeholder(R.drawable.download_icon)
			.into(holder.itemView.sport_news_image_view, object : Callback {
				override fun onSuccess() {

				}

				override fun onError(e: Exception?) {
					Picasso.get()
						.load(newModel.sportnews[position].urlToImage)
						.placeholder(R.drawable.download_icon)
						.into(holder.itemView.sport_news_image_view)
				}
			});


	}


}

class SportNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}