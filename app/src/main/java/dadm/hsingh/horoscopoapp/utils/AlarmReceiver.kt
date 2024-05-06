package dadm.hsingh.horoscopoapp.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.format.DateFormat
import java.util.Calendar
import java.util.concurrent.TimeUnit

private const val NOTIFICACCION = "RECORDATORIO_HOROSCOPO"
private const val CUMPLEACCION = "RECORDATORIO_CUMPLEAÑOS"
private const val EXTRA_BIRTHDAY_NAME = "BIRTHDAY_NAME"

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        when (intent.action) {
            NOTIFICACCION -> {
                generateNotificationReminder(context)
            }
            CUMPLEACCION -> {
                val name = intent.getStringExtra(EXTRA_BIRTHDAY_NAME)?:"???"
                generateNotificationBirthday(context, name)

                val alarmService = AlarmService(context)
                val nextBirthday = Calendar.getInstance()
                nextBirthday.add(Calendar.YEAR, 1)
                alarmService.setBirthdayAlarm(nextBirthday.timeInMillis, name)
            }
        }
    }

}