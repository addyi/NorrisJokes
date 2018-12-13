package de.eosn.norrisjokes.repo

import androidx.lifecycle.LiveData
import arrow.core.Either
import de.eosn.norrisjokes.repo.local.ChuckNorrisJokeDAO
import de.eosn.norrisjokes.repo.remote.ChuckNorrisJokeService

class NorrisRepositoryImpl(
    private val chuckNorrisJokeService: ChuckNorrisJokeService,
    private val chuckNorrisJokeDAO: ChuckNorrisJokeDAO
) : NorrisRepository {

    private val chuckNorrisJokes: LiveData<List<ChuckNorrisJoke>> = chuckNorrisJokeDAO.getAllJokes()

    override suspend fun retrieveJoke(): Either<String, Exception> {

        val response = chuckNorrisJokeService.getRandomJoke().await()

        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                chuckNorrisJokeDAO.insertJokes(body)
                Either.left(body.joke)
            } else {
                Either.right(ParsingException("Somehow joke is null. Joke: $body"))
            }
        } else {
            Either.right(NetworkRequestException("Something went wrong. Response Code: ${response.code()}"))
        }
    }

    override fun getJokes(): LiveData<List<ChuckNorrisJoke>> {
        return chuckNorrisJokes
    }
}

class NetworkRequestException(msg: String = "") : Exception(msg)
class ParsingException(msg: String = "") : Exception(msg)
