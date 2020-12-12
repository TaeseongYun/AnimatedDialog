package tsthec.tsstudy.library

internal interface DialogBuilder {
    fun setTitle(title: CharSequence): AnimatedDialog.Builder?

    fun setImage(resId: Int): AnimatedDialog.Builder?

    fun setPositiveText(text: CharSequence): AnimatedDialog.Builder?

    fun setMessage(message: CharSequence): AnimatedDialog.Builder?

    fun setNegativeText(text: CharSequence): AnimatedDialog.Builder?

    fun setDuration(duration: Duration): AnimatedDialog.Builder?

    fun setPositiveClickListener(listener: setOnPositiveClickListener): AnimatedDialog.Builder?

    fun setNegativeClickListener(listener: setOnNegativeClickListener): AnimatedDialog.Builder?

    fun setWidth(width: Float): AnimatedDialog.Builder?

    fun setHeight(height: Float): AnimatedDialog.Builder?

    fun setPadding(left: Int, right: Int, top: Int, bottom: Int): AnimatedDialog.Builder?

    fun setPositiveBackground(drawable: Int): AnimatedDialog.Builder?

    fun setNegativeBackground(drawable: Int): AnimatedDialog.Builder?
}