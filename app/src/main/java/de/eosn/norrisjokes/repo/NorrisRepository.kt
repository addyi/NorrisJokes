package de.eosn.norrisjokes.repo

import androidx.lifecycle.LiveData
import arrow.core.Either

interface NorrisRepository {
    suspend fun retrieveRandomJoke(): Either<ChuckNorrisJoke, Exception>
    fun getJokes(): LiveData<List<ChuckNorrisJoke>>
}