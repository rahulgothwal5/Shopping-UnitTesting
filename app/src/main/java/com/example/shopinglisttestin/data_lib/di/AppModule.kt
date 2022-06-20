package com.example.shopinglisttestin.data_lib.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.shopinglisttestin.BuildConfig
import com.example.shopinglisttestin.R
import com.example.shopinglisttestin.data_lib.data_source.local.ShoppingDB
import com.example.shopinglisttestin.data_lib.data_source.remote.service.PixaBayService
import com.example.shopinglisttestin.data_lib.utils.Constants.BASE_URL
import com.example.shopinglisttestin.data_lib.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(
        @ApplicationContext context: Context
    ) = Room
        .databaseBuilder(context, ShoppingDB::class.java, DATABASE_NAME)
        .build()

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingDB
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixaBayService(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ) = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(PixaBayService::class.java)

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
    )

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext appContext: Context,
    ): OkHttpClient {
        val spec = ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
            .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
            .cipherSuites(
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA
            )
            .build()
        val okHttpClient = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            }
        }.build()
        val connectionSpecs = okHttpClient.connectionSpecs
        connectionSpecs.map { Collections.singletonList(spec) }
        return okHttpClient
    }


}