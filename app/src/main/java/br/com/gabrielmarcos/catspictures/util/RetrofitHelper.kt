package br.com.gabrielmarcos.catspictures.util

import android.content.Context
import br.com.gabrielmarcos.catspictures.BuildConfig
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Gabriel Marcos on 05/09/2018
 */
class RetrofitHelper {

    companion object {
        fun getRetrofit(context: Context): Retrofit {

            val cacheSize = 10 * 1024 * 1024 // 10 MB
            val httpCacheDirectory = File(context.cacheDir, "http-cache")
            val cache = Cache(httpCacheDirectory, cacheSize.toLong())

            val networkCacheInterceptor = Interceptor { chain ->
                val response = chain.proceed(chain.request())

                val cacheControl = CacheControl.Builder()
                        .maxAge(1, TimeUnit.MINUTES)
                        .build()

                response.newBuilder()
                        .header("Cache-Control", cacheControl.toString())
                        .build()
            }

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                    .cache(cache)
                    .addNetworkInterceptor(networkCacheInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build()
        }

    }
}