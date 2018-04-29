package local.takamk2.selfmanager3.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_top.*
import local.takamk2.selfmanager3.R
import local.takamk2.selfmanager3.viewmodel.TopActivityVM

class TopActivity : AppCompatActivity() {

    lateinit var vm: TopActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)

        vm = ViewModelProviders.of(this).get(TopActivityVM::class.java)

        // データをViewModelにあるdataに送る
        RxView.clicks(redButton).subscribe { vm.postData(1) }
        RxView.clicks(yellowButton).subscribe { vm.postData(2) }
        RxView.clicks(blueButton).subscribe { vm.postData(3) }

        // ViewModelにあるdataを監視。dataが更新されたらViewを更新する
        vm.data.observe(this, Observer {
            var color = Color.BLACK
            when(it) {
                "RED" -> {
                    color = Color.RED
                }
                "YELLOW" -> {
                    color = Color.YELLOW
                }
                "BLUE" -> {
                    color = Color.BLUE
                }
            }
            messageText.setTextColor(color) // Viewの見た目の更新はここに記述する
            messageText.text = it
        })
    }
}
