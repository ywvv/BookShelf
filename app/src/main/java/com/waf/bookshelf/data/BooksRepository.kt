package com.waf.bookshelf.data

import com.waf.bookshelf.network.BookService

interface BooksRepository {
    suspend fun getBooks(query: String, maxResults: Int): List<Book>
}

class NetworkBooksRepository(
    private val bookService: BookService,
) : BooksRepository {
    override suspend fun getBooks(query: String, maxResults: Int): List<Book> {
        val bookShelf = bookService.bookSearch(query, maxResults)
        return bookShelf.items.map { item ->
            Book(
                title = item.volumeInfo?.title,
                previewLink = item.volumeInfo?.previewLink,
                imageLink = item.volumeInfo?.imageLinks?.thumbnail,
            )
        }
    }
}
