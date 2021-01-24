package com.natashaval.futuredatabinding.viewmodel

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natashaval.futuredatabinding.model.User

/**
 * Created by natasha.santoso on 21/01/21.
 */
class MainViewModel : ViewModel() {

    // TODO: A7. move user and score data to viewModel and make score Observable
    val user = User("John", "Doe")

    private var _newScore: MutableLiveData<Int> = MutableLiveData(0)
    val newScore get() = _newScore

    // TODO: A8. Refactor updateScore function and implement event handling Listener Binding
    fun updateScore(value: Int) {
        newScore.value = (newScore.value ?: 0) + value
    }

}

@BindingAdapter("app:hideIfZero")
fun hideIfZero(view: View, score: Int) {
    view.visibility = if (score == 0) View.GONE else View.VISIBLE
}