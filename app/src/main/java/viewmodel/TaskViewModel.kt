package viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    // Other ViewModel code...

    private val _currentPlayingPosition = MutableLiveData<Int?>()
    val currentPlayingPosition: LiveData<Int?> = _currentPlayingPosition

    fun resetCurrentPlayingPosition() {
        _currentPlayingPosition.value = null
    }
}
