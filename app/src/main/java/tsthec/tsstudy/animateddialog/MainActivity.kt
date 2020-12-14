package tsthec.tsstudy.animateddialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_main.*
import tsthec.tsstudy.library.dialog.AnimatedDialog
import tsthec.tsstudy.library.property.DialogSize
import tsthec.tsstudy.library.property.Duration
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val test = BehaviorSubject.create<Long>()

    private val disposable = CompositeDisposable()

    init {
        test.subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.computation())
            .throttleFirst(1000L, TimeUnit.MILLISECONDS)
            .subscribe(::cancelClickListener)
            .addTo(disposable)
    }


    private val dialog by lazy {
        AnimatedDialog.Builder(this, R.style.MyDialogStyle)
            .setTitle("안녕하세요")
            .setNegativeText("취소")
            .setPositiveText("확인")
            .setImage(R.mipmap.ic_launcher)
            .setPositiveBackground(R.drawable.bg_positive)
            .setNegativeBackground(R.drawable.bg_negative)
            .setWidth(resources.displayMetrics.widthPixels.toFloat())
            .setDialogSize(DialogSize.SMALL)
            .setDuration(Duration.SLOW)
            .setPositiveClickListener { }
            .setNegativeClickListener {
                test.onNext(System.currentTimeMillis())
            }
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            dialog.show()
        }
    }

    private fun cancelClickListener(current: Long) {
        if (System.currentTimeMillis() > current) {
            dialog.dismiss()
        }
    }
}