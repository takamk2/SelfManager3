package local.takamk2.selfmanager3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class TopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume: START")
        Timber.d("onResume: END")
    }
}
