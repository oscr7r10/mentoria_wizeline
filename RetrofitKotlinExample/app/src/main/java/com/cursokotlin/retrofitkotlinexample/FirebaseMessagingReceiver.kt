package com.cursokotlin.retrofitkotlinexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.NotificationCompat
import com.cursokotlin.retrofitkotlinexample.presentation.view.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class FirebaseMessagingReceiver : FirebaseMessagingService() {

    private var notificationManager: NotificationManager? = null
    private var idNotify = 1


    companion object {
        private const val CHANNEL_ID_DEFAULT = "CHANNEL_ID_WIZELINE"
        private const val CHANNEL_NAME_DEFAULT = "Informativa"
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        createNotificationManager()
        sendNotificationImage(message)

    }

    private fun createNotificationManager() {
        if (notificationManager == null) {
            notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
    }

    private fun createPendingIntent(): PendingIntent {
        return if (Build.VERSION.SDK_INT >= 12) {
            PendingIntent.getActivity(this, idNotify++, createIntent(), PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(this, idNotify++, createIntent(), PendingIntent.FLAG_ONE_SHOT)
        }
    }

    private fun createIntent(): Intent {
        return Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

    private fun sendNotificationImage(remoteMessage: RemoteMessage) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID_DEFAULT)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
            .setContentTitle("Baz")
            .setContentText("Message baz")
            .setContentIntent(createPendingIntent())
            .setGroup("baz")
            .setAutoCancel(true)

            builder
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("TestMessage")
                )


        createChannel()
        notificationManager!!.notify(idNotify++, builder.build())
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID_DEFAULT,
                CHANNEL_NAME_DEFAULT,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager!!.createNotificationChannel(channel)
        }
    }

}