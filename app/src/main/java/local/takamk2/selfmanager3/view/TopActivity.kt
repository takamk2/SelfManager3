package local.takamk2.selfmanager3.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_top.*
import local.takamk2.selfmanager3.R
import local.takamk2.selfmanager3.TodoActivity
import local.takamk2.selfmanager3.viewmodel.TopActivityVM

class TopActivity : AppCompatActivity() {

    private lateinit var vm: TopActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)

        vm = ViewModelProviders.of(this).get(TopActivityVM::class.java)

        RxView.clicks(go_to_todo_button).subscribe {
            startActivity(Intent(this, TodoActivity::class.java))
        }
    }
}
