package com.waf.bookshelf.ui

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.waf.bookshelf.BookApplication
import com.waf.bookshelf.data.Book
import com.waf.bookshelf.data.BooksRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BooksUiState {
    data class Success(val books: List<Book>) : BooksUiState
    data object Loading : BooksUiState
    data object Error : BooksUiState
}

class BooksViewModel(
    private val booksRepository: BooksRepository,
) : ViewModel() {

    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set

    init {
        getBooks()
    }

    fun getBooks(query: String = "book", maxResults: Int = 40) {
        viewModelScope.launch {
            booksUiState = BooksUiState.Loading
            booksUiState = try {
                BooksUiState.Success(booksRepository.getBooks(query, maxResults))
            } catch (e: IOException) {
                BooksUiState.Error
            } catch (e: HttpException) {
                BooksUiState.Error
            }
        }
    }

    companion object {
        fun createFactory(application: Application): ViewModelProvider.Factory {
            return viewModelFactory {
                initializer {
                    val booksRepository = (application as BookApplication).container.bookRepository
                    BooksViewModel(booksRepository = booksRepository)
                }
            }
        }
    }
}
