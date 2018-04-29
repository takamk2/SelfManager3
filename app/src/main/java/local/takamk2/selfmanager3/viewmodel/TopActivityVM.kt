package local.takamk2.selfmanager3.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

class TopActivityVM(application: Application) : AndroidViewModel(application) {

    val data = MutableLiveData<String>()

    init {
        data.value = "None" // デフォルト値
    }

    fun postData(id: Int) {
        var str = "NONE"
        when(id) { // サンプルなので雑...
            1 -> {
                str = "RED"
            }
            2 -> {
                str = "YELLOW"
            }
            3 -> {
                str = "BLUE"
            }
        }
        data.postValue(str) // View用にデータを変換して購読しているViewに通知
    }
}
