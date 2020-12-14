package tsthec.tsstudy.library

import tsthec.tsstudy.library.dialog.AnimatedDialog
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ViewProperty<T>(private var defaultValue: T) : ReadWriteProperty<AnimatedDialog.Builder, T> {
    override fun getValue(thisRef: AnimatedDialog.Builder, property: KProperty<*>): T {
        return defaultValue
    }

    override fun setValue(thisRef: AnimatedDialog.Builder, property: KProperty<*>, value: T) {
        defaultValue = value
    }
}