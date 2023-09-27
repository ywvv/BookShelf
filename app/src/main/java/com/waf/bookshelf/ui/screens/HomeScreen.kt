package com.waf.bookshelf.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.waf.bookshelf.ui.BooksUiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    booksUiState: BooksUiState,
    retryAction: () -> Unit,
) {
    when (booksUiState) {
        is BooksUiState.Loading -> {
            LoadingScreen(modifier = modifier)
        }

        is BooksUiState.Success -> {
            BooksGridScreen(
                modifier = modifier,
                books = booksUiState.books,
            )
        }

        is BooksUiState.Error -> {
            ErrorScreen(modifier = modifier, retryAction = retryAction)
        }
    }
}
