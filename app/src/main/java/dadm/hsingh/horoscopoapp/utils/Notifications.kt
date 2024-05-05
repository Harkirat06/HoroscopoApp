package dadm.hsingh.horoscopoapp.utils
import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskInfo
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.ui.MainActivity

private val CHANEL_ID = "HOROSCOTIFICACION"
private val NOTIFICATION_ID = 856




fun createNotificationChannel(context: Context) {
        val name = context.getString(R.string.notification_channel_name)
        val channelId = CHANEL_ID
        val descriptionText = context.getString(R.string.notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        val nm: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.createNotificationChannel(channel)
}

fun generateNotification(context: Context?) {
    if (context == null) return
    val channelId = CHANEL_ID
    val largeIcon = BitmapFactory.decodeResource( // (2)
        context.resources,
        R.drawable.estrella_flor
    )

    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)


    val notification = NotificationCompat.Builder(context, channelId) // (3)
        .setLargeIcon(largeIcon) // (4)
        .setSmallIcon(R.drawable.estrella_flor) // (5)
        .setContentTitle(context.getString(R.string.reminder)) // (6)
        .setContentText(context.getString(R.string.reminderContent)) // (7)
        .setContentIntent(pendingIntent)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT) // (9)
        .build()

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return@with
        }
        notify(NOTIFICATION_ID, notification)
    }
}