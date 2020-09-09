package com.example.myportfolio.models

import android.content.Context
import com.example.myportfolio.adapters.forhomepage.HomeFragRVAdapter
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class NewModel(
	val homenewsuz: List<HomeNewsUz>,
	val lastnewsuz: List<LastNewsUz>,
	val worldnews: List<WorldNews>,
	val sportnews: List<SportNews>,
	val locallynews: List<LocallyNews>

) {


	companion object {
		private var newModel: NewModel? = null
		fun init(context: Context) {
			if (newModel == null) {
				val url = "https://next.json-generator.com/api/json/get/Nyia9B67F"
				val request: Request = Request.Builder().url(url).build()
				val client = OkHttpClient()
				client.newCall(request).enqueue(object : Callback {
					override fun onFailure(call: Call, e: IOException) {
					}

					override fun onResponse(call: Call, response: Response) {
						val body = response.body?.string()!!

						val gsonBuilder = GsonBuilder().create()
						newModel = gsonBuilder.fromJson(body, NewModel::class.java)
					}

				})
			}
		}

		fun getInstance() = newModel

	}
}