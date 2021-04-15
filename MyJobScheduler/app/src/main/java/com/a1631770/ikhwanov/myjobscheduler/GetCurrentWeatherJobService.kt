package com.a1631770.ikhwanov.myjobscheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.text.DecimalFormat

class GetCurrentWeatherJobService : JobService() {

    companion object {
        private val TAG = GetCurrentWeatherJobService::class.java.simpleName

        //isi dengan nama kota
        internal const val CITY = "Jakarta"

        //isi dengan API Key anda dari openweathermap
        internal const val APP_ID = "a24c261edb26f53991dcb9bd6224dacb"
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStartJob()")
        getCurrentWeather(params)

        return true
    }


    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStopJob()")
        return true
    }

    private fun getCurrentWeather(params: JobParameters?) {
        Log.d(TAG, "getCurrentWeather: Mulai....!")
        val client = AsyncHttpClient()
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$CITY&appid=$APP_ID"
        Log.d(TAG, "getCurrentWeather: $url")
        client.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int, headers:
                Array<out Header>?,
                responseBody: ByteArray?) {
                val result = String(responseBody!!)
                Log.d(TAG, result)
                try {
                    val responObject = JSONObject(result)
                    val currentWeather = responObject.getJSONArray("weather").getJSONObject(0).getString("main")
                    val desc = responObject.getJSONArray("weather").getJSONObject(0).getString("description")
                    val tempInKelvin = responObject.getJSONObject("main").getDouble("temp")

                    val tempInCelcius = tempInKelvin - 273
                    val temperature = DecimalFormat("##.##").format(tempInCelcius)

                    val title = "current Weather"
                    val message = "$currentWeather, $desc, with $temperature celcius"
                    val notifId = 100

                    showNotification(applicationContext, title, message, notifId)

                    Log.d(TAG, "onSuccess: Selesai.....")
                    jobFinished(params, false)
                } catch (e: Exception) {
                    Log.d(TAG, "onSuccess: Gagal........")
                    jobFinished(params, true)
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?) {
                Log.d(TAG, "onFailure: Gagal......")
                jobFinished(params, true)
            }
        })
    }

    private fun showNotification(context: Context, title: String, message: String, notifId: Int) {
        val CHANNEL_ID = "channel_1"
        val CHANNEL_NAME = "Job scheduler channel"

        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_replay_30_24)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.black))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            builder.setChannelId(CHANNEL_ID)

            notificationManagerCompat.createNotificationChannel(channel)
        }

        val notification =  builder.build()
        notificationManagerCompat.notify(notifId, notification)
    }

}