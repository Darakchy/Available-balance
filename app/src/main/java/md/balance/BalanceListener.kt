package md.balance

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

const val MAIB_PACKAGE = "md.maib.maibank"

class BalanceListener : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        if (sbn.packageName != MAIB_PACKAGE) return
        val extras = sbn.notification.extras
        val text = extras.getCharSequence(Notification.EXTRA_BIG_TEXT)
            ?: extras.getCharSequence(Notification.EXTRA_TEXT) ?: return
        val (card, balance) = parseBalance(text) ?: return
        val prefs = prefs(this)
        if (card != prefs.getString(KEY_CARD, null)) return
        prefs.edit().putString(KEY_BALANCE, balance).putLong(KEY_TIME, sbn.postTime).apply()
        BalanceWidget.updateAll(this)
    }
}
