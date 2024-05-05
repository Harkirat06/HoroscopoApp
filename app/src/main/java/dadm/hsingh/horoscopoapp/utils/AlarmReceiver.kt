package dadm.hsingh.horoscopoapp.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.format.DateFormat
import java.util.Calendar
import java.util.concurrent.TimeUnit

private const val NOTIFICACCION = "RECORDATORIO_HOROSCOPO"
private const val HORA_RECORDATORIO_ID = "HORA_RECORDATORIO"

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val timeInMillis = intent.getLongExtra(HORA_RECORDATORIO_ID, 0L)

        when (intent.action) {
            NOTIFICACCION -> {
                setRepetitiveAlarm(AlarmService(context))
                generateNotification(context)
            }
        }
    }


    private fun setRepetitiveAlarm(alarmService: AlarmService) {
        val cal = Calendar.getInstance().apply {
            this.timeInMillis += TimeUnit.DAYS.toMillis(1)
            // Timber.d("Set alarm for next week same time - ${convertDate(this.timeInMillis)}")
        }
        alarmService.setRepetitiveAlarm(cal.timeInMillis)
    }

    private fun convertDate(timeInMillis: Long): String =
        DateFormat.format("dd/MM/yyyy hh:mm:ss", timeInMillis).toString()

}