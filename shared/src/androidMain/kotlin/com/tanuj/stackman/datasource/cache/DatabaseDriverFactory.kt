package com.tanuj.stackman.datasource.cache

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context){
    actual fun createDriver(): SqlDriver{
        return AndroidSqliteDriver(AppDatabase.Schema, context, "queries.db")
    }
}