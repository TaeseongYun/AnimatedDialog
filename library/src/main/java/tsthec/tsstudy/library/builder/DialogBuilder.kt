package tsthec.tsstudy.library.builder

import tsthec.tsstudy.library.property.DialogSize
import tsthec.tsstudy.library.property.Duration
import tsthec.tsstudy.library.dialog.AnimatedDialog
import tsthec.tsstudy.library.dialog.setOnNegativeClickListener
import tsthec.tsstudy.library.dialog.setOnPositiveClickListener

internal interface DialogBuilder {
    fun setTitle(title: String): AnimatedDialog.Builder?

    fun setImage(resId: Int): AnimatedDialog.Builder?

    fun setPositiveText(text: String): AnimatedDialog.Builder?

    fun setNegativeText(text: String): AnimatedDialog.Builder?

    fun setDuration(duration: Duration): AnimatedDialog.Builder?

    fun setPositiveClickListener(listener: setOnPositiveClickListener): AnimatedDialog.Builder?

    fun setNegativeClickListener(listener: setOnNegativeClickListener): AnimatedDialog.Builder?

    fun setWidth(width: Float): AnimatedDialog.Builder?

    fun setPositiveBackground(drawable: Int): AnimatedDialog.Builder?

    fun setNegativeBackground(drawable: Int): AnimatedDialog.Builder?

    fun setDialogSize(size: DialogSize): AnimatedDialog.Builder?
}