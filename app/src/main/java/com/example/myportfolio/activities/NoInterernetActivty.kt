package com.example.myportfolio.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myportfolio.R
import com.example.myportfolio.models.NewModel

class NoInterernetActivty : AppCompatActivity() {


	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		setContentView(R.layout.no_internet_activity)
	}

	override fun onBackPressed() {
		super.onBackPressed()
				finish()
	}
}
