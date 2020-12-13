package tsthec.tsstudy.library

import android.app.Dialog
import android.content.Context
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.UiThread
import kotlinx.android.synthetic.main.dialog_interface_layout.*
import java.lang.IllegalArgumentException

typealias setOnPositiveClickListener = (View) -> Unit
typealias setOnNegativeClickListener = (View) -> Unit

class AnimatedDialog(private val builder: Builder) :
    Dialog(builder.context, builder.dialogStyle) {

    init {
        builder.dialog = initCustomDialog(builder)
    }

    @UiThread
    override fun show() {
        builder.dialog?.show()
    }

    @UiThread
    override fun dismiss() {
        builder.dialog?.dismiss()
    }

    @UiThread
    private fun initCustomDialog(builder: Builder): Dialog {
        val dialog = Dialog(builder.context, builder.dialogStyle)

        dialog.setContentView(R.layout.dialog_interface_layout)
        dialog.title.text = builder.title
        dialog.image_view.setImageResource(builder.imageResource)
        dialog.positive_button.text = builder.positiveText
        dialog.cancel_button.text = builder.negativeText
        dialog.positive_button.setOnClickListener(builder.positiveClickListener)
        dialog.cancel_button.setOnClickListener(builder.negativeClickListener)

        if (builder.positiveBackground == 0 || builder.negativeBackground == 0) {
            throw IllegalArgumentException("You have to set background positive button and negative button")
        }

        if (builder.positiveBackground != 0 && builder.negativeBackground != 0) {
            dialog.positive_button.setBackgroundResource(builder.positiveBackground)
            dialog.cancel_button.setBackgroundResource(builder.negativeBackground)
        }

        dialog.window!!.attributes.width = builder.width.toInt()
        dialog.window!!.attributes.height = builder.height

        dialog.window!!.attributes.windowAnimations = when (builder.duration) {
            Duration.FAST -> {
                R.style.AnimatedDialog_DialogAnimationFast
            }
            Duration.NORMAL -> {
                R.style.AnimatedDialog_DialogAnimationNormal
            }
            else -> {
                R.style.AnimatedDialog_DialogAnimationSlow
            }
        }

        if (builder.ratio != 0) {
            val dialogHeight = dialog.window!!.attributes.height
            val ratioHeight = dialogHeight / builder.ratio
            dialog.image_view.layoutParams.height = ratioHeight
        }
        return dialog
    }

    class Builder(internal var context: Context, internal val dialogStyle: Int) : DialogBuilder {

        internal var dialog: Dialog? = null

        internal var title: CharSequence? = null
        internal var message: CharSequence? = null
        internal var positiveText: CharSequence? = null
        internal var negativeText: CharSequence? = null
        internal var duration: Duration? = null
        internal var positiveClickListener: setOnPositiveClickListener? = null
        internal var negativeClickListener: setOnNegativeClickListener? = null
        internal var width = 0f
        internal var height = context.resources.displayMetrics.heightPixels
        internal var ratio = 0
        internal var positiveBackground = 0
        internal var negativeBackground = 0
        private var size: DialogSize? = null

        @DrawableRes
        internal var imageResource: Int = 0


        @UiThread
        fun build() = AnimatedDialog(this)

        override fun setTitle(title: CharSequence): Builder {
            this.title = title
            return this
        }

        override fun setImage(resId: Int): Builder {
            this.imageResource = resId
            return this
        }

        override fun setPositiveText(text: CharSequence): Builder {
            this.positiveText = text
            return this
        }

        override fun setMessage(message: CharSequence): Builder {
            this.message = message
            return this
        }

        override fun setNegativeText(text: CharSequence): Builder {
            this.negativeText = text
            return this
        }

        override fun setDuration(duration: Duration): Builder {
            this.duration = duration
            return this
        }

        override fun setPositiveClickListener(listener: setOnPositiveClickListener): Builder {
            this.positiveClickListener = listener
            return this
        }

        override fun setNegativeClickListener(listener: setOnNegativeClickListener): Builder {
            this.negativeClickListener = listener
            return this
        }

        override fun setWidth(width: Float): Builder {
            this.width = width
            return this
        }

        override fun setPositiveBackground(drawable: Int): Builder {
            this.positiveBackground = drawable
            return this
        }

        override fun setNegativeBackground(drawable: Int): Builder {
            this.negativeBackground = drawable
            return this
        }

        override fun setDialogSize(size: DialogSize): Builder {
            this.size = size
            when (size) {
                DialogSize.LARGE -> this.ratio = LARGE
                DialogSize.NORMAL -> this.ratio = NORMAL
                DialogSize.SMALL -> this.ratio = SMALL
            }

            this.height /= this.ratio
            return this
        }

        init {
            duration = Duration.NORMAL
            size = DialogSize.LARGE
        }

        companion object {
            private const val LARGE = 1
            private const val NORMAL = 2
            private const val SMALL = 3
        }
    }
}