package com.tanuj.stackman.datasource

import com.tanuj.stackman.datasource.cache.DatabaseDriverFactory
import com.tanuj.stackman.datasource.cache.Database
import com.tanuj.stackman.datasource.entity.Items
import com.tanuj.stackman.datasource.entity.Response
import com.tanuj.stackman.datasource.network.QueryApi

class QuerySDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = QueryApi()

    @Throws(Exception::class) suspend fun getQueries() : List<Response> {
        val cachedQueries = database.getAllQueries()
        return if (cachedQueries.isNotEmpty()){
            cachedQueries
        }else{
            api.getAllQueries().also {
                database.clearDatabase()
                database.createQueries(it)
            }
        }
    }
}
