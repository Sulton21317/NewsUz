package com.example.myportfolio.adapters.forlocallynews

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
import kotlinx.android.synthetic.main.last_news_frag_item.view.*
import kotlinx.android.synthetic.main.locally_news_frag_item.view.*

@Suppress("DEPRECATION")
class LocallyNewsRVAdapter(val newModel: NewModel) :
	RecyclerView.Adapter<LocallyNewsViewHolder>() {
lateinit var context: Context

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocallyNewsViewHolder {

		context=parent.context

		val itemRootView: View = LayoutInflater.from(parent.context).inflate(
			R.layout.locally_news_frag_item, parent, false
		)

		return LocallyNewsViewHolder(itemRootView);
	}


	override fun getItemCount(): Int {

		return newModel.locallynews.size
	}

	override fun onBindViewHolder(holder: LocallyNewsViewHolder, position: Int) {

		holder.itemView.locally_news_title.text = newModel.locallynews[position].title
		holder.itemView.locally_news_author.text = newModel.locallynews[position].source
		val day = newModel.locallynews[position].publishedAt.subSequence(0, 10) as String
		val date = newModel.locallynews[position].publishedAt.subSequence(11, 16) as String

		holder.itemView.locally_news_published_at.text = day + " " + date



		val myUrl = newModel.locallynews[position].url
		holder.itemView.locally_news_description_full.text =
			Html.fromHtml("<a href=\"$myUrl\">Batafsil</a>")

holder.itemView.locally_news_description_full.setOnClickListener {

			val intent = Intent(Intent.ACTION_VIEW)
			intent.data = Uri.parse(myUrl)
			context.startActivity(intent)


		}
		Picasso.get()
			.load(newModel.locallynews[position].urlToImage)
			.networkPolicy(NetworkPolicy.OFFLINE)
			.placeholder(R.drawable.download_icon)
			.into(holder.itemView.locally_news_image_view, object : Callback {
				override fun onSuccess() {

				}

				override fun onError(e: Exception?) {
					Picasso.get()
						.load(newModel.locallynews[position].urlToImage)
						.placeholder(R.drawable.download_icon)
						.into(holder.itemView.locally_news_image_view)
				}
			});


	}
}


class LocallyNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
}
