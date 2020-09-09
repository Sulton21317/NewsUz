package com.example.myportfolio.app

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import com.example.myportfolio.activities.NoInterernetActivty
import com.example.myportfolio.models.NewModel

@Suppress("DEPRECATION")
class Entery : Application() {

	override fun onCreate() {
		super.onCreate()

		if (isNetworkAvailable()) {
			NewModel.init(this)
		} else {

			if (NewModel.getInstance() == null) {
				val intent = Intent(applicationContext, NoInterernetActivty::class.java)
				startActivity(intent)
			}
		}


	}

	private fun isNetworkAvailable(): Boolean {
		val connectivityManager =
			this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val activeNetworkInfo = connectivityManager.activeNetworkInfo
		return activeNetworkInfo != null && activeNetworkInfo.isConnected
	}

}