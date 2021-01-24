package com.natashaval.futuredatabinding

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val firstNameText = findViewById<TextView>(R.id.tv_first_name)
        firstNameText.text = intent.getStringExtra(SCORE_TITLE_KEY)

        val lastNameText = findViewById<TextView>(R.id.tv_last_name)
        lastNameText.text = intent.getIntExtra(SCORE_KEY, 0).toString()
    }

    companion object {
        const val SCORE_TITLE_KEY = "score_title"
        const val SCORE_KEY = "score"
    }
}