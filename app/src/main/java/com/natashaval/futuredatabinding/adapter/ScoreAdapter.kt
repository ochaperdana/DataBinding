package com.natashaval.futuredatabinding.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.natashaval.futuredatabinding.R

class ScoreAdapter(private val scoreList: List<Int>) :
    RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    class ScoreViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        //private val v: View = view
        val textView: TextView

        init {
            //v.setOnClickListener(this)
            textView = view.findViewById(R.id.tv_score_data)
        }

//        fun bind(value: Int) {
//            textView.text = "Score: $value"
//        }

        override fun onClick(v: View?) {
            textView.text = "Clicked! ${textView.text}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false)
        return ScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        //holder.bind(scoreList[position])
        holder.textView.text = "Score: ${scoreList[position]}"
        Log.d("Scorelist: ", scoreList.size.toString())
    }

    override fun getItemCount(): Int = scoreList.size


}
