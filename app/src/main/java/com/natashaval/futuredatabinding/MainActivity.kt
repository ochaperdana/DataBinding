package com.natashaval.futuredatabinding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.natashaval.futuredatabinding.ProfileActivity.Companion.FIRST_NAME_KEY
import com.natashaval.futuredatabinding.ProfileActivity.Companion.LAST_NAME_KEY
import com.natashaval.futuredatabinding.ScoreActivity.Companion.SCORE_KEY
import com.natashaval.futuredatabinding.ScoreActivity.Companion.SCORE_TITLE_KEY
import com.natashaval.futuredatabinding.adapter.ScoreAdapter
import com.natashaval.futuredatabinding.databinding.ActivityMainBinding
import com.natashaval.futuredatabinding.model.User
import com.natashaval.futuredatabinding.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    private var user = User("John", "Doe")

    //private lateinit var score: TextView
    private lateinit var binding: ActivityMainBinding
    private var mScoreList: MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: A3. change how to inflate with DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.user = user
        binding.viewmodel = viewModel
        binding.activity = this

        // TODO: A4. bind user and score data with name UI
        /*val firstNameText = findViewById<TextView>(R.id.tv_first_name)
        firstNameText.text = user.firstName

        val lastNameText = findViewById<TextView>(R.id.tv_last_name)
        lastNameText.text = user.lastName

        score = findViewById<TextView>(R.id.tv_score)
        score.text = user.score.toString()*/

        //val plusScore = findViewById<Button>(R.id.bt_score_plus)
        binding.btScorePlus.setOnClickListener {
            viewModel.updateScore(1)
        }
        //val minusScore = findViewById<Button>(R.id.bt_score_minus)
        binding.btScoreMinus.setOnClickListener {
            viewModel.updateScore(-1)
        }

        binding.btProfileFragment.setOnClickListener {
            openFragment()
        }

        generateScore()
    }

    private fun generateScore() {
        binding.btGenerateScore.setOnClickListener {
            val lastScore = viewModel.newScore.value ?: 0
            mScoreList.clear()
            for (i in 0..lastScore) {
                mScoreList.add(i)
                Log.d("Score i: ", i.toString())
                Log.d("Score size: ", mScoreList.size.toString())
            }
            Log.d("Score: ", lastScore.toString())
            showScore()
        }
    }

    private fun showScore() {
        val scoreAdapter = ScoreAdapter(mScoreList)
        with(binding.rvScore) {
            adapter = scoreAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 3)
        }
    }

    //  TODO: A5. implement event handling Method References when clicking Activity button
    fun openActivity(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra(FIRST_NAME_KEY, user.firstName)
        intent.putExtra(LAST_NAME_KEY, user.lastName)
        startActivity(intent)
    }

    // TODO: A6. implement setOnClickListener to Fragment button
    fun openFragment() {
        val fragment = ProfileFragment.newInstance(user.firstName, user.lastName)
        fragment.show(supportFragmentManager, ProfileFragment.TAG)
    }

//  private fun updateScore(value: Int) {
//    user.score += value
//    score.text = user.score.toString()
//  }


    private fun openScoreActivity(value: Int) {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra(SCORE_TITLE_KEY, "Score")
        intent.putExtra(SCORE_KEY, value)
        startActivity(intent)
    }
}
