package com.android.ewerton.booksonthetable.repository.webservice

import com.android.ewerton.booksonthetable.model.Book
import com.android.ewerton.booksonthetable.model.dto.BookDto
import com.android.ewerton.booksonthetable.repository.sharedPreferences.AppSharedPreferences
import com.android.ewerton.booksonthetable.repository.util.BOOKS_COLLECTION
import com.android.ewerton.booksonthetable.repository.util.USER_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class BookServiceImp(private val firestore: FirebaseFirestore, private val sharedPreferences: AppSharedPreferences) :
    BookService {
    override suspend fun getAllBooksForUser(): List<Book?> {
        return firestore
            .collection(USER_COLLECTION)
            .document(sharedPreferences.getSignedUserUid()).collection(BOOKS_COLLECTION).get().await()
            .documents.map { it?.toObject(BookDto::class.java)?.toBook()?.apply { UUID = it.id } }
    }

    override suspend fun getBook(UUID: String): Book? {
        return firestore
            .collection(USER_COLLECTION).document(sharedPreferences.getSignedUserUid())
            .collection(BOOKS_COLLECTION).document(UUID).get().await()
            .toObject(BookDto::class.java)?.toBook()
    }

    override suspend fun persistBook(book: Book): Book? {
        book.UUID?.let { uuid ->
            firestore.collection(USER_COLLECTION)
                .document(sharedPreferences.getSignedUserUid()).collection(BOOKS_COLLECTION).document(uuid)
                .update(BookDto.fromBook(book).toMap()).await()

            return book
        }
        val bookFromFirestore = firestore.collection(USER_COLLECTION)
            .document(sharedPreferences.getSignedUserUid()).collection(BOOKS_COLLECTION)
            .add(BookDto.fromBook(book)).await().get()
            .await()

        return bookFromFirestore.toObject(BookDto::class.java)?.toBook()
    }

    override suspend fun deleteBook(UUID: String) {
        firestore.collection(USER_COLLECTION).document(sharedPreferences.getSignedUserUid())
            .collection(BOOKS_COLLECTION).document(UUID).delete()
    }

}