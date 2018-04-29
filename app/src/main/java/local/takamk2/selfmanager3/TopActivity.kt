package local.takamk2.selfmanager3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class TopActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private val sampleSubject = SampleSubject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)

        initButtons()
    }

    override fun onResume() {
        super.onResume()
        if (findViewById<ToggleButton>(R.id.switch_subscribe).isChecked) {
            startSubscribe()
        }
    }

    override fun onStop() {
        stopSubscribe()
        super.onStop()
    }

    private fun initButtons() {
        var count = 0
        findViewById<Button>(R.id.countButton).setOnClickListener {
            count++
            sampleSubject.putData(count)
            Timber.d("count: $count")
        }
        findViewById<ToggleButton>(R.id.switch_subscribe).setOnCheckedChangeListener {
            _, isChecked ->
            if (isChecked) {
                startSubscribe()
            } else {
                stopSubscribe()
            }
        }
    }

    private fun startSubscribe() {
        Timber.d("startSubscribe START")
        // textA側でデータ変更通知を購読
        compositeDisposable.add(sampleSubject.changes().subscribe {
            val msg = "TextA: $it"
            findViewById<TextView>(R.id.textA).text = msg
        })

        // textB側でデータ変更通知を購読
        compositeDisposable.add(sampleSubject.changes().filter { it % 5 == 0 }.map { it / 5 }.subscribe {
            val msg = "TextB: $it"
            findViewById<TextView>(R.id.textB).text = msg
        })
        Timber.d("startSubscribe END")
    }

    private fun stopSubscribe() {
        Timber.d("stopSubscribe START")
        compositeDisposable.clear()
        Timber.d("stopSubscribe END")
    }
}
