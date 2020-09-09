package com.example.myportfolio.adapters.forhomepage

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


@Suppress("DEPRECATION")
class HomeFragRVAdapter(val newModel: NewModel) :
	RecyclerView.Adapter<HomeFragViewHolder>() {

	lateinit var context: Context

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragViewHolder {
		context = parent.context
		val itemRootView: View = LayoutInflater.from(parent.context).inflate(
			R.layout.home_frag_item, parent, false
		)



		return HomeFragViewHolder(itemRootView)

	}


	override fun getItemCount(): Int {
		val count = newModel.homenewsuz.size
		Log.i("myc", count.toString())
		return count
	}



	override fun onBindViewHolder(holder: HomeFragViewHolder, position: Int) {

		holder.itemView.news_title.text = newModel.homenewsuz[position].title
		holder.itemView.news_author.text = newModel.homenewsuz[position].type
		val day = newModel.homenewsuz[position].publishedAt.subSequence(0, 10) as String
		val date = newModel.homenewsuz[position].publishedAt.subSequence(11, 16) as String

		holder.itemView.news_published_at.text = day + " " + date


		holder.itemView.news_description.text = newModel.homenewsuz[position].description
		val myUrl = newModel.homenewsuz[position].url
		holder.itemView.news_description_full.text =
			Html.fromHtml("<a href=\"$myUrl\">Batafsil</a>")
		holder.itemView.news_description_full.setOnClickListener {

			val intent = Intent(android.content.Intent.ACTION_VIEW)
			intent.data = Uri.parse(myUrl)
			context.startActivity(intent)


		}

		Picasso.get()
			.load(newModel.homenewsuz[position].urlToImage)
			.networkPolicy(NetworkPolicy.OFFLINE)
			.placeholder(R.drawable.download_icon)
			.into(holder.itemView.image_view_for_news1, object : Callback {
				override fun onSuccess() {

				}

				override fun onError(e: Exception?) {
					Picasso.get()
						.load(newModel.homenewsuz[position].urlToImage)
						.placeholder(R.drawable.download_icon)
						.into(holder.itemView.image_view_for_news1)
				}
			});


	}
}


class HomeFragViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


}
