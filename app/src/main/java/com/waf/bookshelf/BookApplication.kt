package com.waf.bookshelf

import android.app.Application
import com.waf.bookshelf.data.AppContainer
import com.waf.bookshelf.data.DefaultAppContainer

class BookApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
