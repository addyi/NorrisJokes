package de.eosn.norrisjokes.repo

import androidx.lifecycle.LiveData
import arrow.core.Either

interface NorrisRepository {
    suspend fun retrieveJoke(): Either<String, Exception>
    fun getJokes(): LiveData<List<ChuckNorrisJoke>>
}