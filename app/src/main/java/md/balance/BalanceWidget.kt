package md.balance

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.RemoteViews
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val KEY_CARD = "card"
const val KEY_BALANCE = "balance"
const val KEY_TIME = "time"

fun prefs(context: Context): SharedPreferences = context.getSharedPreferences("balance", Context.MODE_PRIVATE)

class BalanceWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) = updateAll(context)

    companion object {
        fun updateAll(context: Context) {
            val p = prefs(context)
            val card = p.getString(KEY_CARD, null)
            val time = p.getLong(KEY_TIME, 0)
            val views = RemoteViews(context.packageName, R.layout.widget).apply {
                setTextViewText(R.id.balance, p.getString(KEY_BALANCE, null) ?: "—")
                setTextViewText(
                    R.id.info,
                    when {
                        card == null -> "Укажите карту"
                        time == 0L -> "•$card · нет данных"
                        else -> "•$card · " + SimpleDateFormat("dd.MM HH:mm", Locale.getDefault()).format(Date(time))
                    }
                )
                setOnClickPendingIntent(
                    R.id.root,
                    PendingIntent.getActivity(
                        context, 0, Intent(context, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE
                    )
                )
            }
            val manager = AppWidgetManager.getInstance(context)
            manager.updateAppWidget(ComponentName(context, BalanceWidget::class.java), views)
        }
    }
}
