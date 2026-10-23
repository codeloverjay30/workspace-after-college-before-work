package org.example.util.dynamicobject

interface DynamicProperty<T> {
    fun cast(value: Any?): T
    fun default(): T

    companion object {
        inline fun <reified T> fromDefaultSupplier(crossinline default: () -> T) =
            object : DynamicProperty<T> {
                override fun cast(value: Any?): T = value as T
                override fun default(): T = default()
            }

        inline operator fun <reified T> invoke(default: T) = fromDefaultSupplier { default }

        inline fun <reified T> required() = fromDefaultSupplier<T> {
            throw IllegalStateException("DynamicProperty isn't initialized")
        }

        inline fun <reified T> nullable() = DynamicProperty<T?>(null)
    }
}

operator fun <T> DynamicProperty<T>.invoke(value: T) = DynamicPropertyValue(this, value)

data class DynamicPropertyValue<T>(val property: DynamicProperty<T>, val value: T)

class DynamicObject(vararg properties: DynamicPropertyValue<*>) {
    private val properties = HashMap<DynamicProperty<*>, Any?>().apply {
        properties.forEach { put(it.property, it.value) }
    }

    operator fun <T> get(property: DynamicProperty<T>) =
        if (properties.containsKey(property)) property.cast(properties[property])
        else property.default()

    operator fun <T> set(property: DynamicProperty<T>, value: T) = properties.put(property, value)
    operator fun <T> DynamicProperty<T>.minus(value: T) = set(this, value)
}

fun dynamicObj(init: DynamicObject.() -> Unit) = DynamicObject().apply(init)