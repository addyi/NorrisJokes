package de.eosn.norrisjokes.repo

import android.accounts.NetworkErrorException
import arrow.core.Either
import de.eosn.norrisjokes.repo.remote.ChuckNorrisJokeService

class NorrisRepositoryImpl(private val chuckNorrisJokeService: ChuckNorrisJokeService) : NorrisRepository {

    override suspend fun retrieveJoke(): Either<String?, Exception> {

        val request = chuckNorrisJokeService.getRandomJoke()

        val response = request.await()

        return if (response.isSuccessful) {
            Either.left(response.body()?.value)
        } else {
            Either.right(NetworkErrorException("Something went wrong. Response Code: ${response.code()}"))
        }
    }
}

