package com.jay30.appwidgetdemo1

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import android.widget.Toast

/**
 * Implementation of App Widget functionality.
 */
class AppWidgetDemoClass1 : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Toast.makeText(
            context,
            context.getString(R.string.onupdate_message),
            Toast.LENGTH_LONG
        ).show()
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        Toast.makeText(
            context,
            context.getString(R.string.first_widget_is_created_message),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
        Toast.makeText(
            context,
            context.getString(R.string.last_widget_is_disabled_message),
            Toast.LENGTH_LONG
        ).show()
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.app_widget_demo_class1)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}