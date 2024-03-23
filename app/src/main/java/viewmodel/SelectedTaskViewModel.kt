package viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectedTaskViewModel : ViewModel() {
    private val _selectedTaskId = MutableLiveData<Int>()
    val selectedTaskId: LiveData<Int>
        get() = _selectedTaskId

    fun setSelectedTaskId(taskId: Int) {
        _selectedTaskId.value = taskId
    }
}
