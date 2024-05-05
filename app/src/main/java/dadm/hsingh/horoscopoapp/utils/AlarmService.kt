package dadm.hsingh.horoscopoapp.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import dadm.hsingh.horoscopoapp.ui.MainActivity

private const val NOTIFICACCION = "RECORDATORIO_HOROSCOPO"
private const val HORA_RECORDATORIO_ID = "HORA_RECORDATORIO"


class AlarmService (private val context : Context) {

    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?


    //1 Week
    @SuppressLint("ScheduleExactAlarm")
    fun setRepetitiveAlarm(timeInMillis: Long) {

        val alarmIntent = Intent(context, AlarmReceiver::class.java).apply {
            action = NOTIFICACCION
            putExtra(HORA_RECORDATORIO_ID, timeInMillis)
        }
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager?.let {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                pendingIntent
            )
        }
    }

    private fun getPendingIntent(intent: Intent) =
        PendingIntent.getBroadcast(
            context,
            857,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

    @SuppressLint("ScheduleExactAlarm")
    private fun setAlarm(timeInMillis: Long, pendingIntent: PendingIntent) {
        alarmManager?.let {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                pendingIntent
            )
        }
    }

    private fun getIntent() = Intent(context, AlarmReceiver::class.java)

    // private fun getRandomRequestCode() = RandomUtil.getRandomInt()

}

