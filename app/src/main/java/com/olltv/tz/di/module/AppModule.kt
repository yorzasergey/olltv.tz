package com.olltv.tz.di.module

import android.app.Application
import com.olltv.tz.Constants
import com.olltv.tz.data.network.WebService
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideWebServices(
        okHttpClient: OkHttpClient,
        mosh: Moshi
    ): WebService {
        return Retrofit.Builder()
            .baseUrl(Constants.API_DEMO_URL)
            .addConverterFactory(MoshiConverterFactory.create(mosh))
            .client(okHttpClient)
            .build().create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        chuckInterceptor: ChuckInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Singleton
    @Provides
    fun provideMosh(): Moshi {
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideChuckInterceptor(context: Application): ChuckInterceptor {
        return ChuckInterceptor(context)
    }
}