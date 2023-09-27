package com.waf.bookshelf.ui

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.waf.bookshelf.R
import com.waf.bookshelf.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksApp(
    modifier: Modifier = Modifier,
) {
    val app = LocalContext.current.applicationContext as Application
    val viewModelFactory = BooksViewModel.createFactory(app)
    val booksViewModel: BooksViewModel = viewModel(factory = viewModelFactory)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
            )
        },
    ) {
        Surface(
            modifier = Modifier.fillMaxSize().padding(it),
            color = MaterialTheme.colorScheme.background,
        ) {
            HomeScreen(booksUiState = booksViewModel.booksUiState) {
                booksViewModel.getBooks()
            }
        }
    }
}
