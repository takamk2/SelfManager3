package local.takamk2.selfmanager3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.view.RxView

import kotlinx.android.synthetic.main.activity_todo.*
import local.takamk2.selfmanager3.view.dialog.AddTodoDialog

class TodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        setSupportActionBar(toolbar)

        RxView.clicks(fab).subscribe {
            AddTodoDialog().show(supportFragmentManager, AddTodoDialog::class.java.simpleName)
        }
    }
}
