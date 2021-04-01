package com.example.kotlindi

import android.app.Application
import android.util.Log
import com.example.kotlindi.di.networkDependency
import com.example.kotlindi.di.viewmodelModule
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.setAutoLogAppEventsEnabled
import com.facebook.appevents.AppEventsLogger
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private const val baseUrl = "https://kira.store/kira/ankita/"

private fun okHttpClient() =
    OkHttpClient.Builder().run {
        addInterceptor(HttpLoggingInterceptor().apply {
            if (true) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            readTimeout(60L, TimeUnit.SECONDS)
            connectTimeout(60L, TimeUnit.SECONDS)
            writeTimeout(60L, TimeUnit.SECONDS)
        })
        addNetworkInterceptor(StethoInterceptor())
        build()
    }

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().run {
        baseUrl(baseUrl)
        client(httpClient)
        addConverterFactory(
                GsonConverterFactory.create(
                     GsonBuilder()
                        .setLenient()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .create()))
        build()
        //addConverterFactory(GsonConverterFactory.create())
    }


object Singleton{
    fun createRetrofitClient() =
        retrofitClient(baseUrl = baseUrl, okHttpClient())
}

class BaseActivity : Application() {

    companion object{
        lateinit var baseActivity : BaseActivity

        @Synchronized
        fun getInstance(): BaseActivity {
            return baseActivity
        }

    }

    override fun onCreate() {
        super.onCreate()
        baseActivity = this
        com.facebook.stetho.Stetho.initializeWithDefaults(this);

        //facebook stuff
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
        setAutoLogAppEventsEnabled(true)
        initKoin()
    }

    private fun initKoin(){
        startKoin {
            androidContext(this@BaseActivity)
            androidLogger()
            modules(listOf(networkDependency, viewmodelModule))
        }
    }

    fun getCurrentDateTime(date: Date?): String? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val c = Calendar.getInstance()
        c.time = date
        c.add(Calendar.SECOND, -1)
        Log.e("Current Date Time", dateFormat.format(c.time))
        return dateFormat.format(c.time)
    }

}