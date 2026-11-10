package md.balance

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.InputFilter
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val p = prefs(this)

        val cardInput = EditText(this).apply {
            hint = getString(R.string.card_hint)
            inputType = InputType.TYPE_CLASS_NUMBER
            filters = arrayOf(InputFilter.LengthFilter(4))
            setText(p.getString(KEY_CARD, ""))
        }
        val save = Button(this).apply {
            text = getString(R.string.save)
            setOnClickListener {
                val card = cardInput.text.toString()
                if (!Regex("""\d{4}""").matches(card)) {
                    Toast.makeText(this@MainActivity, getString(R.string.need_4_digits), Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                // A different card invalidates the stored balance.
                if (card != p.getString(KEY_CARD, null)) p.edit().clear().putString(KEY_CARD, card).apply()
                BalanceWidget.updateAll(this@MainActivity)
                Toast.makeText(this@MainActivity, getString(R.string.saved), Toast.LENGTH_SHORT).show()
            }
        }
        val access = Button(this).apply {
            text = getString(R.string.grant_access)
            setOnClickListener { startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)) }
        }
        status = TextView(this)

        setContentView(LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            // Android 15+ draws apps edge-to-edge, so keep content below the status bar ourselves.
            setOnApplyWindowInsetsListener { v, insets ->
                v.setPadding(48, 48 + insets.systemWindowInsetTop, 48, 48 + insets.systemWindowInsetBottom)
                insets
            }
            addView(cardInput)
            addView(save)
            addView(access)
            addView(status)
        })
    }

    override fun onResume() {
        super.onResume()
        val granted = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
            ?.contains(packageName) == true
        status.text = getString(if (granted) R.string.access_on else R.string.access_off)
    }
}
