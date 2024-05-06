package dadm.hsingh.horoscopoapp.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import dadm.hsingh.horoscopoapp.ui.MainActivity
import java.util.Calendar

private const val NOTIFICACCION = "RECORDATORIO_HOROSCOPO"
private const val CUMPLEACCION = "RECORDATORIO_CUMPLEAÑOS"
private const val EXTRA_BIRTHDAY_NAME = "BIRTHDAY_NAME"


class AlarmService (private val context : Context) {

    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?
    private val intervalTime = 60 * 1000 // Intervalo entre notificaciones de recordatorio


    //1 Week
    fun setReminderAlarm(timeInMillis: Long) {

        val alarmIntent = Intent(context, AlarmReceiver::class.java).apply {
            action = NOTIFICACCION
        }
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE )

        alarmManager?.let {
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                intervalTime.toLong(),
                pendingIntent
            )
        }
    }

    fun setBirthdayAlarm(nextBirthDayInMillis : Long, birthdayName: String) {


        val alarmIntent = Intent(context, AlarmReceiver::class.java).apply {
            action = CUMPLEACCION
            putExtra(EXTRA_BIRTHDAY_NAME, birthdayName)
        }
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE )

        alarmManager?.let {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                nextBirthDayInMillis,
                pendingIntent
            )
        }
    }

    fun setInmediateAlarm() {
        val timeInMillis : Long = 0
        val alarmIntent = Intent(context, AlarmReceiver::class.java).apply {
            action = NOTIFICACCION
        }
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE )

        alarmManager?.let {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                pendingIntent
            )
        }

    }

 /*   private fun getPendingIntent(intent: Intent) =
        PendingIntent.getBroadcast(
            context,
            857,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
*/

    private fun setAlarm(timeInMillis: Long, pendingIntent: PendingIntent) {
        alarmManager?.let {
            alarmManager.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                pendingIntent
            )
        }
    }

    // private fun getIntent() = Intent(context, AlarmReceiver::class.java)

    // private fun getRandomRequestCode() = RandomUtil.getRandomInt()

}

