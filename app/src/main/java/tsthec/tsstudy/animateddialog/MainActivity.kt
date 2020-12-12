package tsthec.tsstudy.animateddialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import kotlinx.android.synthetic.main.activity_main.*
import tsthec.tsstudy.library.AnimatedDialog
import tsthec.tsstudy.library.Duration

class MainActivity : AppCompatActivity() {

    private val dialog by lazy {
        AnimatedDialog.Builder(this, R.style.MyDialogStyle)
            .setTitle("안녕하세요")
            .setMessage("라이브러리 테스트")
            .setNegativeText("취소")
            .setPositiveText("확인")
            .setImage(R.mipmap.ic_launcher)
            .setPositiveBackground(R.drawable.bg_positive)
            .setNegativeBackground(R.drawable.bg_negative)
            .setWidth(resources.displayMetrics.widthPixels.toFloat())
            .setHeight(resources.displayMetrics.heightPixels.toFloat() / 3)
            .setDuration(Duration.SLOW)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            dialog.show()
        }
    }
}