package de.eosn.norrisjokes.repo

import androidx.lifecycle.LiveData
import arrow.core.Either
import de.eosn.norrisjokes.model.ChuckNorrisJoke

interface NorrisRepository {
    suspend fun retrieveRandomJoke(): Either<ChuckNorrisJoke, Exception>
    fun getJokes(): LiveData<List<ChuckNorrisJoke>>
}