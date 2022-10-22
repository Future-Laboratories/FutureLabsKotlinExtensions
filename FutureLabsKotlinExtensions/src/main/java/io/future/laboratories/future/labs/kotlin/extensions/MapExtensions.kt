package io.future.laboratories.future.labs.kotlin.extensions

public object MapExtensions {
    /**
     * Uses the given nullable Map, filter it by mode, default mode is `Both`
     * @receiver Map<K?, V?>
     * @param mode defines if the filter filters for 'Key', 'Value' or 'Both'
     * @param block optional lambda used on every entry
     * @return a new Map with non-nullable input types
     */
    @Suppress("UNCHECKED_CAST")
    public fun <K : Any, V : Any, M : Map<K?, V?>> M.filterNotNull(
        mode: Mode = Mode.Both,
        block: ((Map.Entry<K, V>) -> Unit)? = null
    ): Map<K, V> {
        val lambda: (Map.Entry<K?, V?>) -> Boolean = when (mode) {
            Mode.Key -> { it -> it.key != null }
            Mode.Value -> { it -> it.value != null }
            Mode.Both -> { it -> it.key != null && it.value != null }
        }

        return emptyMap<K, V>().toMutableMap().also {
            forEach { entry ->
                val filteredEntry = entry.takeIf(lambda) ?: return@forEach
                it += (filteredEntry.toPair()) as Pair<K, V>
                block?.invoke(filteredEntry as Map.Entry<K, V>)
            }
        }
    }

    /**
     * Uses the given Map, filter it by Type. to filter also null see filterNotNullByType
     * @see filterNotNullByType
     * @receiver Map<*, *>
     * @return a new Map<T1, T2> containing only entries that match those types
     */
    @Suppress("UNCHECKED_CAST")
    public inline fun <reified T1, reified T2> Map<*, *>.filterByType(): Map<T1, T2> {
        return filter { entry ->
            entry.key is T1 && entry.value is T2
        } as Map<T1, T2>
    }

    /**
     * Uses the given Map, filter it by Type and nullability, if you want to keep null entries see filterByType
     * @see filterByType
     * @receiver Map<Any?, Any?>
     * @return a new Map<T1, T2> containing only entries that match those types and don't contain null
     */
    @Suppress("UNCHECKED_CAST")
    public inline fun <reified T1 : Any, reified T2 : Any> Map<Any?, Any?>.filterNotNullByType(): Map<T1, T2> {
        return this.filterNotNull { entry ->
            entry.key is T1 && entry.value is T2
        } as Map<T1, T2>
    }

    public enum class Mode {
        Key,
        Value,
        Both,
    }
}