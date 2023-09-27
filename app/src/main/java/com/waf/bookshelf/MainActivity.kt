package com.waf.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.waf.bookshelf.ui.BooksApp
import com.waf.bookshelf.ui.theme.BookShelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookShelfTheme {
                BooksApp()
            }
        }
    }
}
