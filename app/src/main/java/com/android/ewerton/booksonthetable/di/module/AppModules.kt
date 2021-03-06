package com.android.ewerton.booksonthetable.di.module

import com.android.ewerton.booksonthetable.repository.webservice.AuthServiceImp
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.android.ewerton.booksonthetable.BuildConfig
import com.android.ewerton.booksonthetable.repository.BookRepository
import com.android.ewerton.booksonthetable.repository.BookRepositoryImp
import com.android.ewerton.booksonthetable.repository.UserRepository
import com.android.ewerton.booksonthetable.repository.UserRepositoryImp
import com.android.ewerton.booksonthetable.repository.dao.BookDao
import com.android.ewerton.booksonthetable.repository.dao.UserDao
import com.android.ewerton.booksonthetable.repository.db.BooksOnTheTableDatabase
import com.android.ewerton.booksonthetable.repository.sharedPreferences.AppSharedPreferences
import com.android.ewerton.booksonthetable.repository.sharedPreferences.AppSharedPreferencesImp
import com.android.ewerton.booksonthetable.repository.webservice.AuthService
import com.android.ewerton.booksonthetable.repository.webservice.BookService
import com.android.ewerton.booksonthetable.repository.webservice.interceptor.AuthInterceptor
import com.android.ewerton.booksonthetable.ui.activity.access.sign_in.SignInViewModel
import com.android.ewerton.booksonthetable.ui.activity.access.sign_up.SignUpViewModel
import com.android.ewerton.booksonthetable.ui.activity.internal.maintain_book.MaintainBookViewModel
import com.android.ewerton.booksonthetable.ui.activity.internal.user_home.UserHomeViewModel
import com.android.ewerton.booksonthetable.ui.activity.internal.view_book.ViewBookViewModel
import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    fun provideDatabase(application: Application): BooksOnTheTableDatabase {
        return Room.databaseBuilder(
            application,
            BooksOnTheTableDatabase::class.java,
            BuildConfig.MAIN_DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    fun provideUserDao(database: BooksOnTheTableDatabase): UserDao {
        return database.userDao
    }

    fun provideBookDao(database: BooksOnTheTableDatabase): BookDao {
        return database.bookDao
    }

    single {
        provideDatabase(get())
    }

    single {
        provideUserDao(get())
    }

    single {
        provideBookDao(get())
    }
}

val networkModule = module {
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()
    }

    factory { AuthInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single<AuthService>{ AuthServiceImp(FirebaseAuth.getInstance()) }
}

val apiModule = module {
    fun provideBookService(retrofit: Retrofit): BookService {
        return retrofit.create(BookService::class.java)
    }
    single { provideBookService(get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImp(get(),get(),get()) }
    single<BookRepository> { BookRepositoryImp(get()) }
}

val viewModelModule = module {
    viewModel { SignInViewModel(userRepository = get()) }
    viewModel { SignUpViewModel(userRepository = get()) }
    viewModel { UserHomeViewModel(bookRepository = get()) }
    viewModel { ViewBookViewModel(bookRepository = get()) }
    viewModel { MaintainBookViewModel(bookRepository = get()) }
}

val sharedPreferenceModule = module {
    fun provideSharedPreferences(application: Application) =
        application.getSharedPreferences(
            BuildConfig.MAIN_SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )

    single<SharedPreferences> { provideSharedPreferences(get()) }
    single<AppSharedPreferences> { AppSharedPreferencesImp(get()) }
}