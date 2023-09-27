package com.waf.bookshelf.data

import com.waf.bookshelf.network.BookService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val bookRepository: BooksRepository
}

class DefaultAppContainer : AppContainer {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService: BookService by lazy {
        retrofit.create(BookService::class.java)
    }

    override val bookRepository: BooksRepository by lazy {
        NetworkBooksRepository(retrofitService)
    }

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }
}
