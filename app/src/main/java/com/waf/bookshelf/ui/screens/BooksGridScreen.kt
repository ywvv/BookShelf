package com.waf.bookshelf.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.waf.bookshelf.R
import com.waf.bookshelf.data.Book

@Composable
fun BooksGridScreen(
    modifier: Modifier = Modifier,
    books: List<Book>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(4.dp),
        ) {
            itemsIndexed(books) { _, book ->
                BookCard(book = book)
            }
        }
    }
}

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    book: Book,
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .requiredHeight(296.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            book.title?.let { title ->
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 8.dp),
                )
            }
            AsyncImage(
                modifier = modifier.fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(book.imageLink?.replace("http://", "https://"))
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.ic_book_96),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = stringResource(id = R.string.content_description),
                contentScale = ContentScale.Crop,
            )
        }
    }
}
