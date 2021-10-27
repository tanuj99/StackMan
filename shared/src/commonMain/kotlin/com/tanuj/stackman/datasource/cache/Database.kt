package com.tanuj.stackman.datasource.cache

import com.tanuj.stackman.datasource.entity.Items
import com.tanuj.stackman.datasource.entity.Response

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllQueries()
        }
    }

    internal fun getAllQueries(): List<Response> {
        return dbQuery.selectAllQueries(::mapQuerySelecting).executeAsList()
    }

    private fun mapQuerySelecting(
        qId: Long,
        qTitle: String,
        uScore: Long,
    ): Response {
        return Response(
            items = Items(
                question_id = qId,
                title = qTitle,
                score = uScore
            )
        )
    }
    internal fun createQueries(queries:List<Response>){
        dbQuery.transaction {
            queries.forEach { query ->
                insertQuery(query)
            }
        }
    }
    private fun insertQuery(item: Response) {
        dbQuery.insertQuery(
            questionId = item.items.question_id,
            questionTitle = item.items.title,
            upvoteScore = item.items.score
        )
    }
}