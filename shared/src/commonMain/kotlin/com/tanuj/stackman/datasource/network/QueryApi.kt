package com.tanuj.stackman.datasource.network

import com.tanuj.stackman.datasource.entity.Response
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

class QueryApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
            }
        }

    suspend fun getAllQueries(): List<Response>{
        return httpClient.get(QUERY_ENDPOINT)
    }

    companion object {
        private val QUERY_ENDPOINT = Url("https://api.stackexchange.com/2.2/search/advanced?pagesize=100&order=desc&sort=relevance&site=stackoverflow&q=%27Java%20Exception%27")
    }
}