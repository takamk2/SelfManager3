package local.takamk2.selfmanager3.view

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import local.takamk2.selfmanager3.R
import local.takamk2.selfmanager3.viewmodel.TopActivityVM

class TopActivity : AppCompatActivity() {

    private lateinit var vm: TopActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)

        vm = ViewModelProviders.of(this).get(TopActivityVM::class.java)
    }
}
