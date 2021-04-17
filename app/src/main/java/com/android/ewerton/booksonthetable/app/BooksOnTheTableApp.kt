package com.android.ewerton.booksonthetable.app

import android.app.Application
import com.android.ewerton.booksonthetable.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BooksOnTheTableApp :Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BooksOnTheTableApp)
            modules(
                databaseModule,
                repositoryModule,
                viewModelModule,
                networkModule,
                apiModule,
                sharedPreferenceModule,
            )
        }
    }
}