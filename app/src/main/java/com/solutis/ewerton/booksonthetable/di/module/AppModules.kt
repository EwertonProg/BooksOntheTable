package com.solutis.ewerton.booksonthetable.di.module

import android.app.Application
import androidx.room.Room
import com.solutis.ewerton.booksonthetable.BuildConfig
import com.solutis.ewerton.booksonthetable.repository.BookRepository
import com.solutis.ewerton.booksonthetable.repository.BookRepositoryImp
import com.solutis.ewerton.booksonthetable.repository.UserRepository
import com.solutis.ewerton.booksonthetable.repository.UserRepositoryImp
import com.solutis.ewerton.booksonthetable.repository.dao.BookDao
import com.solutis.ewerton.booksonthetable.repository.dao.UserDao
import com.solutis.ewerton.booksonthetable.repository.db.BooksOnTheTableDatabase
import com.solutis.ewerton.booksonthetable.repository.webservice.BookService
import com.solutis.ewerton.booksonthetable.repository.webservice.interceptor.AuthInterceptor
import com.solutis.ewerton.booksonthetable.ui.activity.access.sign_in.SignInViewModel
import com.solutis.ewerton.booksonthetable.ui.activity.access.sign_up.SignUpViewModel
import com.solutis.ewerton.booksonthetable.ui.activity.internal.maintain_book.MaintainBookViewModel
import com.solutis.ewerton.booksonthetable.ui.activity.internal.user_home.UserHomeViewModel
import com.solutis.ewerton.booksonthetable.ui.activity.internal.view_book.ViewBookViewModel
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
            "books_on_the_table_database"
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
}

val apiModule = module {
    fun provideBookService(retrofit: Retrofit): BookService {
        return retrofit.create(BookService::class.java)
    }
    single { provideBookService(get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImp(get()) }
    single<BookRepository> { BookRepositoryImp(get()) }
}

val viewModelModule = module {
    viewModel { SignInViewModel(userRepository = get()) }
    viewModel { SignUpViewModel(userRepository = get()) }
    viewModel { UserHomeViewModel(bookRepository = get()) }
    viewModel { ViewBookViewModel() }
    viewModel { MaintainBookViewModel() }
}