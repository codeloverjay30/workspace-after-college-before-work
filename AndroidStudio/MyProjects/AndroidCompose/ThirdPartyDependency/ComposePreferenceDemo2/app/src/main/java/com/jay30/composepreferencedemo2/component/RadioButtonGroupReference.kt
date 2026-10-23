package com.jay30.composepreferencedemo2.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.zhanghai.compose.preference.RadioButtonPreference

@Composable
fun RadioButtonGroupReference(
    titles: List<@Composable () -> Unit>,
    summaries: List<@Composable() (() -> Unit)?>? ,
    selecteds: List<Boolean>?,
    onClicks: List<() -> Unit>,
    modifier: Modifier = Modifier,
    enableds: List<Boolean>? = null,
    widgetContainer: @Composable() (() -> Unit)? = null,
) {
    val TAG = "tag_RadioButtonGroupReference"
    Log.e(TAG,"titles.size:${titles.size}")
    Log.e(TAG,"selecteds?.size:${selecteds?.size}")

    assert(titles.isNotEmpty()) { "titles parameter is empty."}
    assert(summaries?.isNotEmpty() ?: true) { "summaries parameter is not null and is empty."}
    assert(selecteds?.isNotEmpty() ?: true) { "selecteds parameter is empty."}
    assert(onClicks.isNotEmpty()) { "onClicks parameter is empty."}

    assert(summaries == null || titles.size== summaries.size) { "summaries parameter is not null AND size of titles parameter is not same as size of summaries parameter."}
    assert(selecteds == null || titles.size == selecteds.size) { "size of titles parameter is not same as size of selecteds parameter."}
    assert(titles.size == onClicks.size) { "size of titles parameter is not same as size of onClicks parameter."}

    for (index in titles.indices) {
        val title = titles[index]
        val summary = summaries?.getOrNull(index)
        val selected = selecteds?.getOrNull(index) ?: false
        val onClick = onClicks[index]
        val enabled = enableds?.getOrNull(index) ?: true

        RadioButtonPreference(
            title = title,
            modifier = modifier,
            enabled = enabled,
            selected = selected,
            onClick = onClick,
            summary = summary,
            widgetContainer = widgetContainer
        )
    }
}