package de.eosn.norrisjokes.repo.remote

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisJokeService {

    @GET("/jokes/random")
    fun getRandomJoke(): Deferred<Response<ChuckNorrisJoke>>

    @GET("/jokes/random?category={category}")
    fun getRandomJokeFor(@Query("category") category: String): Deferred<Response<ChuckNorrisJoke>>
}